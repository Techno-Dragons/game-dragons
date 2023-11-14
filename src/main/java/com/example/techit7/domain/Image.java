package com.example.techit7.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.*;

@Entity @Getter
@RequiredArgsConstructor
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
