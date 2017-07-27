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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhd on 2017/7/26.
 */

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private ClassifyServiceImpl classifyService;

    @Autowired
    private BaseController base;

    /**
     * 标签列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/tag-list")
    public String tagPage(Model model){
        List<Tag> tags = tagService.selectTagList();
        model.addAttribute("tags",tags);
        return "tag/tag";
    }

    /**
     * 拥有这个tag的文章
     * @param model
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/tag-list-article/{tagId}")
    public String tagList(Model model, @PathVariable("tagId") String tagId){
        Tag tag = tagService.selectTagByTagId(tagId);
        List<String> articleIds = tagService.selectArticleIdByTagId(tag.getId());
        model.addAttribute("articles",base.getArticleUseArticleId(articleIds));
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
