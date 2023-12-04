package com.example.techit7.article.service;

import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.dto.ArticleResponseDto;
import com.example.techit7.user.User;
import java.util.List;

public interface ArticleService{

    /**
     * 게시글 전체조회
     * @PARAM X
     * @RETURN List<Article>
     */
    List<ArticleResponseDto> getArticles();

    /**
     * 게시글 단일조회
     * @PARAM Long Id
     * @RETURN Article
     */
    ArticleResponseDto getArticleById(Long id);

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
    Long postArticle(ArticleRequestDto articleRequestDto, User user);
}
