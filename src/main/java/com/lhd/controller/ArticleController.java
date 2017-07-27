package com.lhd.controller;

import com.lhd.dto.ArchiveDTO;
import com.lhd.dto.ArticleDTO;
import com.lhd.entity.Classify;
import com.lhd.entity.Tag;
import com.lhd.service.Impl.ArticleServiceImpl;
import com.lhd.service.Impl.ClassifyServiceImpl;
import com.lhd.service.Impl.FriendServiceImpl;
import com.lhd.service.Impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.*;


/**
 * Created by lhd on 2017/5/16.
 * 文章管理controller
 */

@Controller
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private ClassifyServiceImpl classifyService;

    @Autowired
    private BaseController base;

    /**
     * 分类查询所有博客
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/article-classify/{classifyId}")
    public String classifyPage(Model model,String pageNo,@PathVariable("classifyId") String classifyId){
        int no;
        if(pageNo != null) no = Integer.parseInt(pageNo);
        else no = 1;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startNo",(no-1)*4);
        paramMap.put("pageSize",20);
        paramMap.put("classifyId",classifyId);
        List<ArticleDTO> articleDTOS = articleService.selectArticleDtoList(paramMap);
        base.addAllTagChain(articleDTOS);
        model.addAttribute("articles", articleDTOS);
        return "common";
    }

    /**
     * 文章详情
     * @return
     */
    @RequestMapping(value = "/article-detail/{articleId}")
    public String articleDetail(Model model,@PathVariable("articleId")String articleId) throws IOException {
        ArticleDTO articleDTO = articleService.selectArticleDto(articleId);
        articleDTO.setContent(base.getMarkdown(articleDTO.getContent()));
        model.addAttribute("article", articleDTO);
        return "blog/article";
    }



    /**
     * 每个月的博客信息,归档
     * @return
     */
    @RequestMapping(value = "/archive-month/{year}/{month}")
    public String archiveMonth(Model model,@PathVariable("year") String year,
                               @PathVariable("month") String month){
        int aYear = Integer.parseInt(year);
        int aMonth = Integer.parseInt(month);
        ArchiveDTO archiveDTO = new ArchiveDTO();
        archiveDTO.setaYear(aYear);
        archiveDTO.setaMonth(aMonth);
        List<ArticleDTO> articles = articleService.selectArticleYM(archiveDTO);
        base.addAllTagChain(articles);
        model.addAttribute("articles",articles);
        return "common";
    }



    /**
     * 将归档情况信息放入session级别中，就不用每次获取数据
     * @return
     */
    @ModelAttribute(name = "archives")
    public List<ArchiveDTO> setModelArchives(){

        return base.getArchive();
    }

    /**
     * 将分组情况信息放入session级别中，就不用每次获取数据
     * @return
     */
    @ModelAttribute(name = "classifies")
    public List<Classify> setModelClassifies(){
        List<Classify> classifies = classifyService.selectClassifyList();
        base.addCount(classifies);
        return classifies;
    }

}
