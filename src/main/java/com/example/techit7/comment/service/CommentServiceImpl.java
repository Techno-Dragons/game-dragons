package com.example.techit7.comment.service;

import com.example.techit7.article.entity.Article;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.user.entity.SiteUser;
import com.example.techit7.comment.dto.CommentResponseDto;
import com.example.techit7.comment.entity.Comment;
import com.example.techit7.comment.repository.CommentRepository;
import com.example.techit7.global.dto.GlobalResponseDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment get(Long id) {
        return validateById(id);
    }

    @Override
    public GlobalResponseDto<Comment> post(SiteUser siteUser, Article article, CommentRequestDto req) {

        Comment comment = Comment.builder()
                .author(siteUser)
                .article(article)
                .content(req.getContent())
                .build();

        commentRepository.save(comment);
        return CommentResponseDto.of("200", "정상");
    }

    @Override
    public GlobalResponseDto<Comment> update(SiteUser siteUser, Long id, CommentRequestDto req) {
        Comment comment = validateById(id);
        validateByUser(siteUser, comment);

        comment = comment.toBuilder()
                .content(req.getContent())
                .build();
        commentRepository.save(comment);
        return CommentResponseDto.of("200", "정상");
    }

    @Override
    public GlobalResponseDto<Comment> delete(SiteUser siteUser, Long id) {
        Comment comment = validateById(id);
        validateByUser(siteUser, comment);

        commentRepository.delete(comment);
        return CommentResponseDto.of("200", "정상");
    }

    public Comment validateById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()){
            return comment.get();
        }
        else {
            throw new IllegalArgumentException("COMMENT NOT FOUND");
        }
    }

    public void validateByUser(SiteUser siteUser, Comment comment) {
        if (!siteUser.getUsername().equals(comment.getAuthor().getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다");
        }
    }
}
