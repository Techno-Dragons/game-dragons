package com.example.techit7.comment;


import com.example.techit7.article.Article;
import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_regdate")
    private Date regDate;

    @Column(name = "comment_moddate")
    private Date modDate;
}
