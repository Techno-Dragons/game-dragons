package com.example.techit7.comment.service;

import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.entity.Comment;
import com.example.techit7.comment.repository.CommentRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    @Override
    public Comment getComment(Long id) {
        return getValidateComment(id);
    }

    @Override
    public void postComment(CommentRequestDto commentRequestDto) {
        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .build();

        // comment.addComment(); ? DDD 방식 설계와 충돌, ArticleRepository 의존 필요
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Long id, CommentRequestDto rq) {
        Comment comment = getValidateComment(id);
        comment.modify(rq.getContent());
    }

    @Override
    public void deleteComment(Long id) {
        getValidateComment(id);
        commentRepository.deleteById(id);
    }

    public Comment getValidateComment(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()){
            return comment.get();
        }
        else {
            throw new IllegalArgumentException("COMMENT NOT FOUND");
        }
    }
}
