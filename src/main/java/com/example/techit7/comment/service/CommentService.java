package com.example.techit7.comment.service;

import com.example.techit7.article.entity.Article;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.dto.CommentResponseDto;
import com.example.techit7.comment.entity.Comment;

import com.example.techit7.user.entity.SiteUser;

public interface CommentService {

    Comment get(Long id);

    CommentResponseDto post(SiteUser siteUser, Article article, CommentRequestDto req);

    CommentResponseDto update(SiteUser siteUser, Long id, CommentRequestDto req);

    void delete(SiteUser siteUser, Long id);
}
