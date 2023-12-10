package com.example.techit7.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용을 입력해 주세요")
    @Size(max = 500, message = "댓글은 500자를 초과할 수 없습니다")
    private String content;
}
