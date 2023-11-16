package com.example.techit7.article.entity;


import com.example.techit7.article.entity.Article;
import lombok.*;
import jakarta.persistence.*;

@Entity @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
