package com.example.techit7.comment.entity;


import com.example.techit7.article.entity.Article;
import com.example.techit7.user.User;
import com.example.techit7.util.DateTime;
import jakarta.persistence.*;
import java.util.Date;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Entity @Getter @Builder
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

    @CreatedDate
    @Column(name = "comment_regdate")
    private Date regDate;

    @Column(name = "comment_moddate")
    private DateTime modDate;

    public void addComment(Article article) {
        this.article = article;
        article.getCommentList().add(this);
    }
}
