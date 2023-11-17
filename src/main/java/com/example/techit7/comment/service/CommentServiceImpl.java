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
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()){
            return comment.get();
        }
        else {
            throw new IllegalArgumentException("COMMENT NOT FOUND");
        }
    }

    @Override
    public void postComment(CommentRequestDto commentRequestDto) {
        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Long id) {

    }

    @Override
    public void deleteComment(Long id) {

    }
}
