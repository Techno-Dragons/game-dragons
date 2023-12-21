package com.example.techit7.comment.service;

import com.example.techit7.article.entity.Article;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.user.entity.Member;
import com.example.techit7.comment.dto.CommentResponseDto;
import com.example.techit7.comment.entity.Comment;
import com.example.techit7.comment.repository.CommentRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentRestServiceImpl implements CommentRestService {
    private final CommentRepository commentRepository;

    @Override
    public Comment get(Long id) {
        return validateById(id);
    }

    @Override
    public CommentResponseDto post(Member member, Article article, CommentRequestDto req) {

        Comment comment = Comment.builder()
                .author(member)
                .article(article)
                .content(req.getContent())
                .build();

        commentRepository.save(comment);

        return CommentResponseDto
                .builder()
                .id(comment.getId())
                .authorName(member.getUsername())
                .content(comment.getContent())
                .build();
    }

    @Override
    public CommentResponseDto update(Member member, Long id, CommentRequestDto req) {
        Comment comment = validateById(id);
        validateByUser(member, comment);

        comment = comment.toBuilder()
                .content(req.getContent())
                .build();

        commentRepository.save(comment);

        return CommentResponseDto
                .builder()
                .id(comment.getId())
                .authorName(member.getUsername())
                .content(comment.getContent())
                .build();
    }

    @Override
    public void delete(Member member, Long id) {
        Comment comment = validateById(id);
        validateByUser(member, comment);

        commentRepository.delete(comment);
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

    public void validateByUser(Member member, Comment comment) {
        if (!member.getUsername().equals(comment.getAuthor().getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다");
        }
    }
}
