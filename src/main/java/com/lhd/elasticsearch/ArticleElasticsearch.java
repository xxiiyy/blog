package com.lhd.elasticsearch;

import com.lhd.dto.ArticleDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by lhd on 2017/7/26.
 * 文章es搜索
 */
public interface ArticleElasticsearch extends ElasticsearchRepository<ArticleDTO,String>{


    /**
     * 文章搜索
     * @param title
     * @param subTitle
     * @param author
     * @return
     */
    List<ArticleDTO> findAllByTitleOrSubtitleOrAuthor(
            String title,String subTitle,String author);

}
