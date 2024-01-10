package com.example.techit7.comment.service;

import com.example.techit7.article.entity.Article;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.dto.CommentResponseDto;
import com.example.techit7.comment.entity.Comment;

import com.example.techit7.user.entity.Member;

public interface CommentRestService {

	Comment get(Long id);

	CommentResponseDto post(Member member, Article article, CommentRequestDto req);

	CommentResponseDto update(Member member, Long id, CommentRequestDto req);

	void delete(Member member, Long id);
}
