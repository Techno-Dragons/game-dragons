package com.example.techit7.article.dto;

import com.example.techit7.comment.entity.Comment;
import com.example.techit7.global.dto.GlobalResponseDto;
import com.example.techit7.user.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
//뭘 보여줄 지 몰라서 골라내야함
public class ArticleResponseDto extends GlobalResponseDto<ArticleResponseDto> {

    private User author;    //글쓴이
    private String title;   //제목
    private String content; //내용
    private Long viewCount; //조회수
    private Long likeCount; //좋아요 수
    private String category;//카테고리
    private List<Comment> commentList = new ArrayList<>();  //댓글리스트

}
