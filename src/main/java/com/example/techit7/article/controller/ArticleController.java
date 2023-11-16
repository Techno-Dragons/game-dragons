package com.example.techit7.article.controller;


import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.service.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleServiceImpl articleService;

    @PostMapping("/article")
    public void createArticle(@RequestBody ArticleRequestDto articleRequestDto,
                              Principal principal){

        return;
    }
}
