package com.example.techit7.comment.service;

import com.example.techit7.article.entity.Article;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.dto.CommentResponseDto;
import com.example.techit7.comment.entity.Comment;

import com.example.techit7.global.response.GlobalResponseDto;
import com.example.techit7.user.User;
import java.util.List;

public interface CommentService {

    Comment get(Long id);

    GlobalResponseDto<Comment> post(User user, Article article, CommentRequestDto req);

    GlobalResponseDto<Comment> update(Long id, CommentRequestDto req);

    GlobalResponseDto<Comment> delete(Long id);

}
