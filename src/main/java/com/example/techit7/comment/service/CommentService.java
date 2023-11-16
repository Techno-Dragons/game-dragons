package com.example.techit7.comment.service;

import com.example.techit7.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 댓글 조회
     * @PARAM X
     * @RETURN List<Comment>
     */
    List<Comment> getComments(Long id);

    /**
     * 댓글 작성
     * @PARAM X
     * @RETURN X
     */
    void postComment();

    /**
     * 댓글 수정
     * @PARAM Long Id
     * @RETURN X
     */
    void updateComment(Long id);

    /**
     * 댓글 삭제
     * @PARAM Long Id
     * @RETURN X
     */
    void deleteComment(Long id);

}
