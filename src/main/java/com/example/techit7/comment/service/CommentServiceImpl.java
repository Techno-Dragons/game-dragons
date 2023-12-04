package com.example.techit7.comment.service;

import com.example.techit7.article.entity.Article;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.dto.CommentResponseDto;
import com.example.techit7.comment.entity.Comment;
import com.example.techit7.comment.repository.CommentRepository;
import com.example.techit7.global.response.GlobalResponseDto;
import com.example.techit7.user.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    @Override
    public Comment get(Long id) {
        return validate(id);
    }

    @Override
    public GlobalResponseDto<Comment> post(User user, Article article, CommentRequestDto req) {
        Comment comment = Comment.builder()
                .author(user)
                .article(article)
                .content(req.getContent())
                .build();

        commentRepository.save(comment);
        return CommentResponseDto.of("200", "정상");
    }

    @Override
    public GlobalResponseDto<Comment> update(Long id, CommentRequestDto req) {
        Comment comment = validate(id);
        comment = comment.toBuilder()
                .content(req.getContent())
                .build();
        commentRepository.save(comment);
        return CommentResponseDto.of("200", "정상");
    }

    @Override
    public GlobalResponseDto<Comment> delete(Long id) {
        validate(id);
        return CommentResponseDto.of("200", "정상");
    }

    public Comment validate(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()){
            return comment.get();
        }
        else {
            throw new IllegalArgumentException("COMMENT NOT FOUND");
        }
    }
}
