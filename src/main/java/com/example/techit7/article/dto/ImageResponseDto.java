package com.example.techit7.article.dto;

import com.example.techit7.article.entity.Article;
import com.example.techit7.global.response.GlobalResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponseDto extends GlobalResponse<ImageResponseDto> {

	private Long id;

	private Article article;

	private String storeFilename;

}
