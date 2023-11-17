package com.example.techit7.article.entity;

import com.example.techit7.comment.Comment;
import com.example.techit7.user.User;
import com.example.techit7.util.DateTime;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article extends DateTime{
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
    private List<Comment> commentList = new ArrayList<>();


    //view카운트 증가
    public void incrementViewCount() {
        if (viewCount == null) {
            viewCount = 0L;
        }
        
        viewCount++;
    }

    //like카운트 증가
    public void incrementLikeCount() {
        if (likeCount == null) {
            likeCount = 0L;
        }

        likeCount++;
    }

}
