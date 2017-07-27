package com.lhd.controller;

import com.lhd.dto.ArchiveDTO;
import com.lhd.dto.ArticleDTO;
import com.lhd.entity.Classify;
import com.lhd.entity.Tag;
import com.lhd.service.Impl.ArticleServiceImpl;
import com.lhd.service.Impl.ClassifyServiceImpl;
import com.lhd.service.Impl.FriendServiceImpl;
import com.lhd.service.Impl.TagServiceImpl;
import com.lhd.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.*;
import java.util.*;

/**
 * Created by lhd on 2017/7/26.
 */

@Controller
public class BaseController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private ClassifyServiceImpl classifyService;

    @Autowired
    private TagServiceImpl tagService;



    /**
     * 查询所有博客，分页查询
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = {"","index"})
    public String index(Model model, String pageNo){
        int no;
        if(pageNo != null) no = Integer.parseInt(pageNo);
        else no = 1;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startNo",(no-1)*4);
        paramMap.put("pageSize",4);
        List<ArticleDTO> articleDTOS = articleService.selectArticleDtoList(paramMap);
        addAllTagChain(articleDTOS);
        int count = articleService.selectCountByClassifyId(new HashMap<>());
        String pagination = PageUtils.getPagination(no, "index", count/4+1);
        model.addAttribute("pagination",pagination);
        model.addAttribute("articles", articleDTOS);
        return "index";
    }

    /**
     * 将归档情况信息放入session级别中，就不用每次获取数据
     * @return
     */
    @ModelAttribute(name = "archives")
    public List<ArchiveDTO> setModelArchives(){
        return getArchive();
    }

    /**
     * 将分组情况信息放入session级别中，就不用每次获取数据
     * @return
     */
    @ModelAttribute(name = "classifies")
    public List<Classify> setModelClassifies(){
        List<Classify> classifies = classifyService.selectClassifyList();
        addCount(classifies);
        return classifies;
    }

    /**
     * 获取归档信息
     * @return
     */
    public List<ArchiveDTO> getArchive(){
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        List<ArchiveDTO> archives = new ArrayList<>();
        for (int year = nowYear; year >= 2017; year-- ){
            List<ArchiveDTO> archiveDTOS = articleService.selectArchive(year);
            archives.addAll(archiveDTOS);
        }
        // articleService.selectArchive();
        return archives;
    }

    /**
     * 添加classify属性，添加这个分类一共有多少篇博客
     * @param classifies
     */
    public void addCount(List<Classify> classifies){
        for (Classify c:classifies){
            Map<String,Object> objectMap = new HashMap<>();
            objectMap.put("classifyId",c.getId());
            int count = articleService.selectCountByClassifyId(objectMap);
            c.setNum(count);
        }
    }

    /**
     * 遍历全部的article
     * @param articles
     */
    public void addAllTagChain(List<ArticleDTO> articles){
        for (ArticleDTO article:articles){
            String tagChain = getTagChain(tagService.selectTagIdByArticleId(article.getId()));
            article.setTagChain(tagChain);
        }
    }

    /**
     * 获取标签链
     * @param tagIdList
     * @return
     */
    public String getTagChain(List<String> tagIdList){
        String tagChain = "";
        for (String tagId:tagIdList){
            Tag tag = tagService.selectTagByTagId(tagId);
            if(tag != null&&tag.getTagName() != null)
                tagChain += " "+tag.getTagName();
        }
        return tagChain.trim();
    }

    /**
     * 获取markdown文件信息
     * @param rootPath
     * @return
     * @throws IOException
     */
    public String getMarkdown(String rootPath) throws IOException {
        File file = new File(rootPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file),"utf-8"));
        StringBuffer suf = new StringBuffer();
        String content;
        while((content = br.readLine())!=null){
            suf.append(content);
            suf.append("\n");
        }
        br.close();
        return suf.toString();
    }

    /**
     * 使用文章id集合获取文章集合
     * @param articleIds
     * @return
     */
    public List<ArticleDTO> getArticleUseArticleId(List<String> articleIds){
        List<ArticleDTO> articles = new ArrayList<>();
        for (String articleId:articleIds){
            ArticleDTO article = articleService.selectArticleDto(articleId);
            articles.add(article);
        }
        addAllTagChain(articles);
        return articles;
    }



}
