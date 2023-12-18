package com.example.techit7.article.service;

import static com.example.techit7.article.errormessage.ErrorMessage.ENTITY_NOT_FOUND;

import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.dto.ArticleResponseDto;
import com.example.techit7.article.entity.Article;
import com.example.techit7.article.repository.ArticleRepository;
import com.example.techit7.global.response.GlobalResponseDto;
import com.example.techit7.user.entity.SiteUser;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    /**
     * 게시글 전체조회
     * @return List<ArticleResponseDto>
     */
//    @Override
//    public List<GlobalResponseDto<ArticleResponseDto>> getArticles() {
//        List<Article> articles = articleRepository.findAll();
//
//        List<GlobalResponseDto<ArticleResponseDto>> articleResponseDtos = new ArrayList<>();
//        for (Article article : articles) {
//            ArticleResponseDto articleResponseDto = getArticleResponse(article);
//
//            articleResponseDtos.add(articleResponseDto);
//        }
//
//        return articleResponseDtos;
//    }

    public GlobalResponseDto<Page<ArticleResponseDto>> getArticles(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdTime"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        Page<Article> articles = articleRepository.findAll(pageable);

        return GlobalResponseDto.of("200", "success", articles.map(this::getArticleResponse));
    }

    /**
     * 게시글 단일조회
     * @param id    조회할 게시글 ID
     * @return ArticleResponseDto
     */
    public GlobalResponseDto<ArticleResponseDto> getArticleResponseById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND + id);
        }

        return GlobalResponseDto.of("200", "success", getArticleResponse(article.get())) ;
    }
    @Override
    public GlobalResponseDto<ArticleResponseDto> getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND + id);
        }

        return GlobalResponseDto.of("200", "success", getArticleResponse(article.get())) ;
    }

    public Article findArticleById(Long id){
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND + id);
        }
        return article.get();
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
            throw new EntityNotFoundException(ENTITY_NOT_FOUND + id);
        }

        article.get().modifyArticle(articleRequestDto);
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
            throw new EntityNotFoundException(ENTITY_NOT_FOUND + id);
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
    public Long postArticle(ArticleRequestDto articleRequestDto, SiteUser author) {
        Article article = Article.builder()
                .author(author)
                .title(articleRequestDto.getTitle())
                .content(articleRequestDto.getContent())
                .category(articleRequestDto.getCategory())
                .likeCount(0L)
                .viewCount(0L)
                .commentList(new ArrayList<>())
                .build();

        articleRepository.save(article);
        return article.getId();
    }


    private ArticleResponseDto getArticleResponse(Article article) {
        return ArticleResponseDto.builder()
                .id(article.getId())
                .author(article.getAuthor())
                .title(article.getTitle())
                .content(article.getContent())
                .category(article.getCategory())
                .commentList(article.getCommentList())
                .createdTime(article.getCreatedTime())
                .modifiedTime(article.getModifiedTime())
                .build();

    }
}
