package com.example.techit7.article.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDto {

    @NotEmpty(message = "제목을 입력하세요")
    @Length(max = 20)
    private String title;       //제목

    @NotEmpty(message = "내용을 입력하세요")
    @Length(max= 500)
    private String content;     //내용
    private String category;    //카테고리
    private MultipartFile multipartFile;
}
