package com.example.techit7.article.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Getter
@NoArgsConstructor
@Data
public class ArticleRequestDto {

    private String title;       //제목
    private String content;     //내용
    private String category;    //카테고리
    private MultipartFile multipartFile;
}
