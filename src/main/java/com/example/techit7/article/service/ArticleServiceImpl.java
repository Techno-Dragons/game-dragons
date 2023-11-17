package com.example.techit7.article.service;

import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.dto.ArticleResponseDto;
import com.example.techit7.article.entity.Article;
import com.example.techit7.article.repository.ArticleRepository;
import com.example.techit7.article.service.ArticleService;
import com.example.techit7.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    /**
     * 게시글 전체조히
     * @return List<ArticleResponseDto>
     */
    @Override
    public List<ArticleResponseDto> getArticles() {
        List<Article> articles = articleRepository.findAll();

        List<ArticleResponseDto> articleResponseDtos = new ArrayList<>();
        for (Article article : articles) {
            ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                    .author(article.getAuthor())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .category(article.getCategory())
                    .commentList(article.getCommentList())
                    .build();

            articleResponseDtos.add(articleResponseDto);
        }

        return articleResponseDtos;
    }

    /**
     * 게시글 단일조회
     * @param id    조회할 게시글 ID
     * @return ArticleResponseDto
     */
    @Override
    public ArticleResponseDto getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new EntityNotFoundException("Entity not found with id" + id);
        }

        ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                .author(article.get().getAuthor())
                .title(article.get().getTitle())
                .content(article.get().getContent())
                .category(article.get().getCategory())
                .commentList(article.get().getCommentList())
                .build();

        return articleResponseDto;
    }

    /**
     * 게시글 수정
     * @param id    수정할 게시글 ID
     * @param articleRequestDto 유저로 부터 받은 수정값
     */
    @Override
    @Transactional
    public void updateArticleById(Long id, ArticleRequestDto articleRequestDto) {
        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new EntityNotFoundException("Entity not found with id " + id);
        }

        articleRepository.save(article.get());
    }

    /**
     * 게시글 삭제
     * @param id 삭제할 게시글 ID
     */
    @Override
    @Transactional
    public void deleteArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new EntityNotFoundException("Entity not found With id " + id);
        }
        articleRepository.delete(article.get());
    }

    /**
     * 게시글 작성
     * @param articleRequestDto 요청을 받은 값
     * @param author    요청을 보낸 유저
     */
    @Override
    @Transactional
    public void postArticle(ArticleRequestDto articleRequestDto, User author) {
        Article article = Article.builder()
                .author(author)
                .title(articleRequestDto.getTitle())
                .content(articleRequestDto.getContent())
                .category(articleRequestDto.getCategory())
                .likeCount(0L)
                .viewCount(0L)
                .build();

        articleRepository.save(article);
    }


    private ArticleResponseDto getArticleResponse(Article article) {
        ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                .author(article.getAuthor())
                .title(article.getTitle())
                .content(article.getContent())
                .category(article.getCategory())
                .commentList(article.getCommentList())
                .build();

        return articleResponseDto;
    }
}