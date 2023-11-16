package com.example.techit7.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ArticleRequestDto {

    private String title;   //제목
    private String content; //내용
    private String category;    //카테고리
}
