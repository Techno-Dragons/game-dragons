package com.example.techit7.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ArticleRequestDto {

    private String title;
    private String content;
    private String category;
}
