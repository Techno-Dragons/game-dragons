package com.example.techit7.article.repository;

import com.example.techit7.article.entity.Image;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByArticleId(Long articleId);
}
