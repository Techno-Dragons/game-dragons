package com.example.techit7.comment.service;

import com.example.techit7.comment.entity.Comment;
import com.example.techit7.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getComments(Long articleId) {
        return this.commentRepository.findByArticleId(articleId);
    }

    @Override
    public void postComment() {

    }

    @Override
    public void updateComment(Long id) {

    }

    @Override
    public void deleteComment(Long id) {

    }
}
