package com.example.techit7.article.service;

import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.dto.ArticleResponseDto;
import com.example.techit7.global.response.GlobalResponseDto;
import com.example.techit7.user.entity.SiteUser;
import org.springframework.data.domain.Page;

public interface ArticleService{

    /**
     * 게시글 전체조회
     * @PARAM X
     * @RETURN List<Article>
     */
    GlobalResponseDto<Page<ArticleResponseDto>> getArticles(int page);

    /**
     * 게시글 단일조회
     * @PARAM Long Id
     * @RETURN Article
     */
    GlobalResponseDto<ArticleResponseDto> getArticleById(Long id);

    /**
     * 게시글 수정
     * @PARAM Long Id
     * @RETURN X
     */
    void updateArticleById(Long id, ArticleRequestDto articleRequestDto);

    /**
     * 게시글 삭제
     * @PARAM Long Id
     * @RETURN X
     */
    void deleteArticleById(Long id);

    /**
     * 게시글 작성
     * @PARAM X
     * @RETURN X
     */
    Long postArticle(ArticleRequestDto articleRequestDto, SiteUser user);
}
