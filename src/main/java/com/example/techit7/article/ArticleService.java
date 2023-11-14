package com.example.techit7.article;

import java.util.List;

public interface ArticleService{

    /**
     * 게시글 전체조회
     * @PARAM X
     * @RETURN List<Article>
     */
    List<Article> getArticles();

    /**
     * 게시글 단일조회
     * @PARAM Long Id
     * @RETURN Article
     */
    Article getArticleById();

    /**
     * 게시글 수정
     * @PARAM Long Id
     * @RETURN X
     */
    void updateArticleById();

    /**
     * 게시글 삭제
     * @PARAM Long Id
     * @RETURN X
     */
    void deleteArticleById();

    /**
     * 게시글 작성
     * @PARAM X
     * @RETURN X
     */
    void postArticle();
}
