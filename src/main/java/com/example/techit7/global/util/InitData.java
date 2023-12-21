package com.example.techit7.global.util;

import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.service.ArticleServiceImpl;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.service.CommentRestServiceImpl;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.entity.Member;
import com.example.techit7.user.service.MemberRestServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class InitData {
    private final ArticleServiceImpl articleService;
    private final CommentRestServiceImpl commentService;
    private final MemberRestServiceImpl memberRestService;
    @Bean
    public ApplicationRunner run() {
        return new ApplicationRunner() {
            @Override
            @Transactional
            @SneakyThrows
            public void run(ApplicationArguments args) {

                // test user 데이터 생성
                UserCreateRequestDto dtoAdmin = new UserCreateRequestDto() {{
                    setUsername("admin");
                    setPassword1("1234");
                    setPassword2("1234");
                    setEmail("admin@test.com");
                    setNickname("admin");
                }};
                memberRestService.signup(dtoAdmin);

                UserCreateRequestDto dtoTestUser1 = new UserCreateRequestDto() {{
                    setUsername("user1");
                    setPassword1("1234");
                    setPassword2("1234");
                    setEmail("user1@test.com");
                    setNickname("user1");
                }};
                memberRestService.signup(dtoTestUser1);

                // test 게시글 데이터 생성
                Member user1 = memberRestService.findByUsername("user1");
                for (int i = 1; i <= 100; i++) {
                    ArticleRequestDto articleRequestDto = new ArticleRequestDto();
                    articleRequestDto.setCategory("total");
                    articleRequestDto.setTitle(String.format("테스트 글 %d", i));
                    articleRequestDto.setContent("content");
                    articleRequestDto.setMultipartFile(null);

                    // test 댓글 데이터 생성
                    Long articleId = articleService.postArticle(articleRequestDto, user1);
                    for (int j = 1; j <= 3; j++) {
                        CommentRequestDto commentRequestDto = new CommentRequestDto();
                        commentRequestDto.setContent(String.format("테스트 댓글 %d", i));
                        commentService.post(user1, articleService.findArticleById(articleId), commentRequestDto);
                    }
                }
            }
        };
    }
}