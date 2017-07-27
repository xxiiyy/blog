package com.lhd.service.Impl;

import com.lhd.dto.ArchiveDTO;
import com.lhd.dto.ArticleDTO;
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
    public List<ArticleDTO> selectArticleDtoList(Map<String, Object> paramMap) {
        return articleMapper.selectArticleDtoList(paramMap);
    }

    @Override
    public int selectCountByClassifyId(Map<String, Object> paramMap) {
        return articleMapper.selectCountByClassifyId(paramMap);
    }

    @Override
    public ArticleDTO selectArticleDto(String articleId) {
        return articleMapper.selectArticleDto(articleId);
    }

    @Override
    public List<ArchiveDTO> selectArchive(int year) {
        return articleMapper.selectArchive(year);
    }

    @Override
    public List<ArticleDTO> selectArticleYM(ArchiveDTO archiveDTO) {
        return articleMapper.selectArticleYM(archiveDTO);
    }
}
