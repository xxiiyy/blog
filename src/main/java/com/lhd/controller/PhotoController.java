package com.lhd.controller;

import com.lhd.dto.ArchiveDTO;
import com.lhd.entity.Classify;
import com.lhd.service.Impl.ArticleServiceImpl;
import com.lhd.service.Impl.ClassifyServiceImpl;
import com.lhd.service.Impl.FriendServiceImpl;
import com.lhd.service.Impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by lhd on 2017/7/26.
 */
@Controller
@RequestMapping("/photo")
public class PhotoController {


    @Autowired
    private ClassifyServiceImpl classifyService;

    @Autowired
    private BaseController base;

    @RequestMapping(value = "/photo")
    public String blogPhoto(){
        return "photo/photo";
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
