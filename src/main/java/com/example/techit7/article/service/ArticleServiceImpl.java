package com.example.techit7.article.service;

import static com.example.techit7.article.errormessage.ErrorMessage.ENTITY_NOT_FOUND;

import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.dto.ArticleResponseDto;
import com.example.techit7.article.entity.Article;
import com.example.techit7.article.repository.ArticleRepository;
import com.example.techit7.global.response.GlobalResponse;
import com.example.techit7.user.entity.Member;
import com.example.techit7.user.service.MemberRestServiceImpl;

import io.jsonwebtoken.security.Jwks.OP;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

	public Page<Article> getArticles(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createdTime"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

		Page<Article> articles = articleRepository.findAll(pageable);

		return articles;
	}

	/**
	 * 게시글 단일조회
	 * @param id    조회할 게시글 ID
	 * @return ArticleResponseDto
	 */
	public GlobalResponse getArticleResponseById(Long id) {
		Optional<Article> article = articleRepository.findById(id);
		if (article.isEmpty()) {
			throw new EntityNotFoundException(ENTITY_NOT_FOUND + id);
		}

		return GlobalResponse.of("200", "success");
	}

	public Article findArticleById(Long id) {
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
	public void updateArticleById(Long id, ArticleRequestDto articleRequestDto, String username) {
		Optional<Article> article = articleRepository.findById(id);

		if (article.isEmpty()) {
			throw new EntityNotFoundException(ENTITY_NOT_FOUND + id);
		}
		if (!article.get().getAuthor().getUsername().equals(username)) {
			throw new IllegalArgumentException("권한이 없는 유저입니다.");
		}

		article.get().modifyArticle(articleRequestDto);
	}

	/**
	 * 게시글 삭제
	 * @param id 삭제할 게시글 ID
	 */
	@Override
	@Transactional
	public void deleteArticleById(Long id, String username) {

		Optional<Article> article = articleRepository.findById(id);

		if (article.isEmpty()) {
			throw new EntityNotFoundException(ENTITY_NOT_FOUND + id);
		}
		if (!article.get().getAuthor().getUsername().equals(username)) {
			throw new IllegalArgumentException("권한이 없는 유저입니다.");
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
	public Long postArticle(ArticleRequestDto articleRequestDto, Member author) {
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
