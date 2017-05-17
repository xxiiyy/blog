package com.lhd.mapper;


import com.lhd.dto.ArchiveDto;
import com.lhd.dto.ArticleDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lhd on 2017/5/14.
 * 文章查询
 */
public interface ArticleMapper{

    /**
     * 获取博客列表
     * @param paramMap
     * @return
     */
    List<ArticleDto> selectArticleDtoList(@Param("paramMap") Map<String,Object> paramMap);

    /**
     * 获取这个分类下一共有多少
     * @param paramMap
     * @return
     */
    int selectCountByClassifyId(@Param("paramMap") Map<String,Object> paramMap);

    /**
     * 使用id查询
     * @return
     */
    ArticleDto selectArticleDto(@Param("articleId") String articleId);

    /**
     * 获取归档信息
     * @param year
     * @return
     */
    List<ArchiveDto> selectArchive(@Param("year") int year);

    /**
     * 返回某年某月的博客信息
     * @return
     */
    List<ArticleDto> selectArticleYM(ArchiveDto archiveDto);

}
