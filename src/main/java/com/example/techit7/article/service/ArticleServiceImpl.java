package com.example.techit7.article.service;

import com.example.techit7.article.entity.Article;
import com.example.techit7.article.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Override
    public List<Article> getArticles() {
        return null;
    }

    @Override
    public Article getArticleById(Long id) {
        return null;
    }

    @Override
    public void updateArticleById(Long id) {

    }

    @Override
    public void deleteArticleById(Long id) {

    }


    @Override
    public void postArticle() {

    }
}
