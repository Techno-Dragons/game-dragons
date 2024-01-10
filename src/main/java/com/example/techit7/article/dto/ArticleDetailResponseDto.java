package com.example.techit7.article.dto;

import com.example.techit7.article.entity.Article;
import com.example.techit7.article.entity.Image;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ArticleDetailResponseDto {

	Article article;

	Image image;

}
