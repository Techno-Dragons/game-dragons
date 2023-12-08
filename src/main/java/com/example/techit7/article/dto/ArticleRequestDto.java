package com.example.techit7.article.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Getter
@NoArgsConstructor
@Data
public class ArticleRequestDto {

    @NotEmpty(message = "제목을 입력해주세요")
    private String title;       //제목
    @NotEmpty(message = "내용을 입력해주세요")
    private String content;     //내용
    private String category;    //카테고리
    private MultipartFile multipartFile;
}
