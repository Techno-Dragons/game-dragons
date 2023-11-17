package com.example.techit7.comment.service;

import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 댓글 조회
     * @PARAM Long id
     * @RETURN Comment
     */
    Comment getComment(Long id);

    /**
     * 댓글 작성
     * @PARAM CommentRequestDto
     * @RETURN X
     */
    void postComment(CommentRequestDto commentRequestDto);

    /**
     * 댓글 수정
     * @PARAM Long Id
     * @RETURN X
     */
    void updateComment(Long id, CommentRequestDto commentRequestDto);

    /**
     * 댓글 삭제
     * @PARAM Long Id
     * @RETURN X
     */
    void deleteComment(Long id);

}
