package com.example.techit7.article;

import com.example.techit7.comment.Comment;
import com.example.techit7.user.User;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Date regDate;

    @Column
    private Date modDate;

    @Column
    private Long viewCount;

    @Column
    private Long likeCount;

    @Column
    private String category;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

}
