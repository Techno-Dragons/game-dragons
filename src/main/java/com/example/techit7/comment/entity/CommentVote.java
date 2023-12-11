package com.example.techit7.comment.entity;

import com.example.techit7.user.entity.SiteUser;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private SiteUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
