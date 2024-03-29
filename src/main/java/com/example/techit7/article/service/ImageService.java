package com.example.techit7.article.service;

import com.example.techit7.article.dto.ImageResponseDto;
import com.example.techit7.article.entity.Article;
import com.example.techit7.article.entity.Image;
import com.example.techit7.article.errormessage.ErrorMessage;
import com.example.techit7.article.repository.ArticleRepository;
import com.example.techit7.article.repository.ImageRepository;
import com.example.techit7.global.config.GoogleCloudStorageConfig;
import com.example.techit7.global.config.GoogleCloudStorageProperties;
import com.example.techit7.global.util.FileStore;
import com.google.cloud.storage.BlobInfo;

import jakarta.persistence.EntityNotFoundException;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {
	private final ImageRepository imageRepository;
	private final ArticleRepository articleRepository;
	private final FileStore fileStore;
	private final GoogleCloudStorageConfig googleCloudStorageConfig;
	private final GoogleCloudStorageProperties googleCloudStorageProperties;

	// 이미지 저장
	@Transactional
	public void save(MultipartFile multipartFile, Long articleId) throws IOException {

		String storeFilename = "";
		if (multipartFile != null) {
			storeFilename = uploadImage(multipartFile);
		}

		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		Image image = Image.builder()
			.article(article.get())
			.storeFilename(storeFilename)
			.build();

		imageRepository.save(image);
	}

	public String uploadImage(MultipartFile image) throws IOException {

		String storeFilename = UUID.randomUUID().toString();
		String ext = image.getContentType();

		BlobInfo blobInfo = googleCloudStorageConfig.googleCloudStorage().create(
			BlobInfo.newBuilder(googleCloudStorageProperties.getBUCKET_NAME(), storeFilename)
				.setContentType(ext)
				.build(),
			image.getInputStream());

		return storeFilename;
	}

	@Transactional
	public void update(MultipartFile multipartFile, Long articleId, String username) throws IOException {
		if (multipartFile == null || multipartFile.isEmpty()) {
			return;
		}

		Optional<Image> image = imageRepository.findByArticleId(articleId);
		if (image.isEmpty()) {
			save(multipartFile, articleId);
			return;
		}

		if (!image.get().getArticle().getAuthor().getUsername().equals(username)) {
			throw new IllegalArgumentException("권한이 없는 유저입니다.");
		}

		image.get().updateImage(uploadImage(multipartFile));
	}

	// 이미지 삭제
	@Transactional
	public void delete(Long articleId, String username) {
		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		if (!article.get().getAuthor().getUsername().equals(username)) {
			throw new IllegalArgumentException("권한이 없는 유저입니다.");
		}
		Optional<Image> image = imageRepository.findByArticleId(articleId);
		if (image.isEmpty()) {
			return;
		}
		imageRepository.delete(image.get());
	}

	// Article ID로 연결된 Image 얻기
	public Image getByArticleId(Long articleId) {
		Optional<Image> image = imageRepository.findByArticleId(articleId);
		if (image.isEmpty()) {
			return null;
		}

		return image.get();
	}

	//이미지 Path 얻기
	public String getFullPathStoreFilenameByArticleId(Long articleId) {
		String storeFilename = getByArticleId(articleId).getStoreFilename();
		return fileStore.getFullPath(storeFilename);
	}
}
