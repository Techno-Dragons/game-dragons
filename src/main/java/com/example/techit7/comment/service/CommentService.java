package com.example.techit7.comment.service;

import com.example.techit7.article.entity.Article;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.entity.Comment;

import com.example.techit7.global.dto.GlobalResponseDto;
import com.example.techit7.user.entity.SiteUser;

public interface CommentService {

    Comment get(Long id);

    GlobalResponseDto<Comment> post(SiteUser siteUser, Article article, CommentRequestDto req);

    GlobalResponseDto<Comment> update(SiteUser siteUser, Long id, CommentRequestDto req);

    GlobalResponseDto<Comment> delete(SiteUser siteUser, Long id);

}
