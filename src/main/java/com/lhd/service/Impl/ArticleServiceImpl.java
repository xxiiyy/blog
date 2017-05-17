package com.lhd.service.Impl;

import com.lhd.dto.ArchiveDto;
import com.lhd.dto.ArticleDto;
import com.lhd.mapper.ArticleMapper;
import com.lhd.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by lhd on 2017/5/14.
 * 文章查询
 */
@Service
public class ArticleServiceImpl implements IArticleService{

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<ArticleDto> selectArticleDtoList(Map<String, Object> paramMap) {
        return articleMapper.selectArticleDtoList(paramMap);
    }

    @Override
    public int selectCountByClassifyId(Map<String, Object> paramMap) {
        return articleMapper.selectCountByClassifyId(paramMap);
    }

    @Override
    public ArticleDto selectArticleDto(String articleId) {
        return articleMapper.selectArticleDto(articleId);
    }

    @Override
    public List<ArchiveDto> selectArchive(int year) {
        return articleMapper.selectArchive(year);
    }

    @Override
    public List<ArticleDto> selectArticleYM(ArchiveDto archiveDto) {
        return articleMapper.selectArticleYM(archiveDto);
    }
}
