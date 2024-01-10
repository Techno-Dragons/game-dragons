package com.example.techit7.comment.repository;

import com.example.techit7.comment.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByArticleId(Long articleId);

}
