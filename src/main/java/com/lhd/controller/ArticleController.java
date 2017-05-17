package com.lhd.controller;

import com.lhd.dto.ArchiveDto;
import com.lhd.dto.ArticleDto;
import com.lhd.entity.Classify;
import com.lhd.entity.Friend;
import com.lhd.entity.Tag;
import com.lhd.service.Impl.ArticleServiceImpl;
import com.lhd.service.Impl.ClassifyServiceImpl;
import com.lhd.service.Impl.FriendServiceImpl;
import com.lhd.service.Impl.TagServiceImpl;
import com.lhd.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;


/**
 * Created by lhd on 2017/5/16.
 * 文章管理controller
 */

@Controller
@RequestMapping("/")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private ClassifyServiceImpl classifyService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private FriendServiceImpl friendService;

    /**
     * 查询所有博客
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = {"","index"})
    public String index(Model model,String pageNo){
        int no;
        if(pageNo != null) no = Integer.parseInt(pageNo);
        else no = 1;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startNo",(no-1)*4);
        paramMap.put("pageSize",4);
        List<ArticleDto> articleDtos = articleService.selectArticleDtoList(paramMap);
        List<Classify> classifies = classifyService.selectClassifyList();
        addCount(classifies);
        int count = articleService.selectCountByClassifyId(new HashMap<>());
        String pagination = PageUtils.getPagination(no, "index", count);
        model.addAttribute("archives",getArchive());
        model.addAttribute("pagination",pagination);
        model.addAttribute("classifies",classifies);
        model.addAttribute("articles",articleDtos);
        return "article/index";
    }

    /**
     * 分类查询所有博客
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "article-classify/{classifyId}")
    public String classifyPage(Model model,String pageNo,@PathVariable("classifyId") String classifyId){
        int no;
        if(pageNo != null) no = Integer.parseInt(pageNo);
        else no = 1;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startNo",(no-1)*4);
        paramMap.put("pageSize",20);
        paramMap.put("classifyId",classifyId);
        List<ArticleDto> articleDtos = articleService.selectArticleDtoList(paramMap);
        List<Classify> classifies = classifyService.selectClassifyList();
        addCount(classifies);
        model.addAttribute("archives",getArchive());
        model.addAttribute("classifies",classifies);
        model.addAttribute("articles",articleDtos);
        return "article/common";
    }

    /**
     * 标签列表
     * @param model
     * @return
     */
    @RequestMapping(value = "article-tag")
    public String tagPage(Model model){
        List<Tag> tags = tagService.selectTagList();
        List<Classify> classifies = classifyService.selectClassifyList();
        addCount(classifies);
        model.addAttribute("archives",getArchive());
        model.addAttribute("classifies",classifies);
        model.addAttribute("tags",tags);
        return "article/tag";
    }

    /**
     * 文章详情
     * @return
     */
    @RequestMapping(value = "article-detail/{articleId}")
    public String articleDetail(Model model,@PathVariable("articleId")String articleId){
        ArticleDto articleDto = articleService.selectArticleDto(articleId);
        model.addAttribute("article",articleDto);
        List<Classify> classifies = classifyService.selectClassifyList();
        addCount(classifies);
        model.addAttribute("archives",getArchive());
        model.addAttribute("classifies",classifies);
        return "article/article";
    }

    /**
     * 转到友链界面
     * @param model
     * @return
     */
    @RequestMapping(value = "blog-friend")
    public String friend(Model model){
        List<Classify> classifies = classifyService.selectClassifyList();
        List<Friend> friends = friendService.selectFriendList();
        addCount(classifies);
        model.addAttribute("archives",getArchive());
        model.addAttribute("friends",friends);
        model.addAttribute("classifies",classifies);
        return "article/friend";
    }

    /**
     * 每个月的博客信息
     * @return
     */
    @RequestMapping(value = "archive-month/{year}/{month}")
    public String archiveMonth(Model model,@PathVariable("year") String year,
                               @PathVariable("month") String month){
        int aYear = Integer.parseInt(year);
        int aMonth = Integer.parseInt(month);
        ArchiveDto archiveDto = new ArchiveDto();
        archiveDto.setaYear(aYear);
        archiveDto.setaMonth(aMonth);
        List<Classify> classifies = classifyService.selectClassifyList();
        addCount(classifies);
        model.addAttribute("archives",getArchive());
        model.addAttribute("classifies",classifies);
        List<ArticleDto> articles = articleService.selectArticleYM(archiveDto);
        model.addAttribute("articles",articles);
        return "article/common";
    }

    @RequestMapping(value = "blog-about")
    public String blogAbout(Model model){
        List<Classify> classifies = classifyService.selectClassifyList();
        addCount(classifies);
        model.addAttribute("archives",getArchive());
        model.addAttribute("classifies",classifies);
        return "article/about";
    }

    private List<ArchiveDto> getArchive(){
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        List<ArchiveDto> archives = new ArrayList<>();
        for (int year = nowYear; year >= 2017; year-- ){
            List<ArchiveDto> archiveDtos = articleService.selectArchive(year);
            archives.addAll(archiveDtos);
        }
       // articleService.selectArchive();
        return archives;
    }

    /**
     * 添加classify属性
     * @param classifies
     */
    private void addCount(List<Classify> classifies){
        for (Classify c:classifies){
            Map<String,Object> objectMap = new HashMap<>();
            objectMap.put("classifyId",c.getId());
            int count = articleService.selectCountByClassifyId(objectMap);
            c.setNum(count);
        }
    }

}
