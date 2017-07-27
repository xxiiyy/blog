package com.lhd.controller;

import com.lhd.dto.ArchiveDTO;
import com.lhd.dto.ArticleDTO;
import com.lhd.elasticsearch.ArticleElasticsearch;
import com.lhd.entity.Classify;
import com.lhd.entity.Tag;
import com.lhd.service.Impl.ArticleServiceImpl;
import com.lhd.service.Impl.ClassifyServiceImpl;
import com.lhd.service.Impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by lhd on 2017/7/26.
 */
@Controller
public class SearchController {

    @Autowired
    private ArticleElasticsearch articleElasticsearch;

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private ClassifyServiceImpl classifyService;

    @Autowired
    private BaseController base;

    /**
     * 添加一个文章
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public ArticleDTO save(){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId("2e7265ef9eee455fa5e378abb93ecbcd");
        articleDTO.setTitle("maven环境配置");
        articleDTO.setContent("f:/blog/md/2017/4/18/1495108302395.md");
        articleDTO.setSubtitle("maven环境配置");
        articleDTO.setAuthor("lhd");
        articleDTO.setTagChain("web");
        articleDTO.setClassify("java");
        articleDTO.setClick(0);
        articleDTO.setCover("/images/random/bg1.jpg");
        articleDTO.setCreateTime(new Date());
        articleDTO.setUpdateTime(new Date());
        articleDTO.setCreateBy("lhd");
        articleDTO.setUpdateBy("lhd");
        articleElasticsearch.save(articleDTO);
        return articleDTO;
    }

    @RequestMapping(value = "/search/{info}")
    @ResponseBody
    public List<ArticleDTO> search(@PathVariable("info") String info){
        List<ArticleDTO> articleDTOS = articleElasticsearch.findAllByTitleOrSubtitleOrAuthor(info, info, info);
        return articleDTOS;
    }

    @RequestMapping(value = "/search")
    public String search(){
        return "search/search";
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
