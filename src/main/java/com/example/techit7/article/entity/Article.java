package com.example.techit7.article.entity;

import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.comment.entity.Comment;
import com.example.techit7.global.entity.BaseEntity;
import com.example.techit7.user.entity.SiteUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(targetEntity = SiteUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SiteUser author;

    @Column(columnDefinition = "TEXT", length = 20)
    private String title;

    @Column(columnDefinition = "TEXT", length = 500)
    private String content;

    @Column
    private Long viewCount;

    @Column
    private Long likeCount;

    @Column
    private String category;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @Builder.Default
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

    //게시글 수정
    public void modifyArticle(ArticleRequestDto articleRequestDto) {
        if (StringUtils.hasText(articleRequestDto.getTitle())) {
            this.title = articleRequestDto.getTitle();
        }
        if (StringUtils.hasText(articleRequestDto.getContent())) {
            this.title = this.content = articleRequestDto.getContent();
        }
        if (StringUtils.hasText(articleRequestDto.getCategory())) {
            this.title = this.category = articleRequestDto.getCategory();
        }

    }

}
