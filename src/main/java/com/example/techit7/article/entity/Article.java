package com.example.techit7.article.entity;

import com.example.techit7.comment.entity.Comment;
import com.example.techit7.global.entity.BaseEntity;
import com.example.techit7.user.User;
import com.example.techit7.global.entity.DateTime;
import jakarta.persistence.*;
import java.util.List;

import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article extends BaseEntity {
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

//    @Column
//    private Date regDate;

//    @Column
//    private Date modDate;

//    @Column
//    @Embedded
//    private DateTime regDate;

    @Column
    private Long viewCount;

    @Column
    private Long likeCount;

    @Column
    private String category;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

}
