package com.example.techit7.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
	@NotBlank(message = "댓글 내용을 입력해 주세요")
	@Length(max = 500, message = "댓글은 500자를 초과할 수 없습니다")
	private String content;
}
