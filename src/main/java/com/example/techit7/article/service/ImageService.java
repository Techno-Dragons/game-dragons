package com.example.techit7.article.service;

import com.example.techit7.article.dto.ImageResponseDto;
import com.example.techit7.article.entity.Article;
import com.example.techit7.article.entity.Image;
import com.example.techit7.article.errormessage.ErrorMessage;
import com.example.techit7.article.repository.ArticleRepository;
import com.example.techit7.article.repository.ImageRepository;
import com.example.techit7.global.dto.GlobalResponseDto;
import com.example.techit7.global.util.FileStore;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;
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

    // 이미지 저장
    @Transactional
    public void save(MultipartFile multipartFile, Long articleId) throws IOException {

        if (multipartFile == null || multipartFile.isEmpty()) {
            return;
        }

        String storeFilename = fileStore.storeFile(multipartFile);
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

    @Transactional
    public void update(MultipartFile multipartFile, Long articleId) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return;
        }

        Optional<Image> image = imageRepository.findByArticleId(articleId);
        if (image.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
        }

        image.get().updateImage(fileStore.storeFile(multipartFile));
    }

    // 이미지 삭제
    @Transactional
    public void delete(Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
        }
        Optional<Image> image = imageRepository.findByArticleId(articleId);
        if (image.isEmpty()) {
            return;
        }
        imageRepository.delete(image.get());
    }

    // Article ID로 연결된 Image 얻기
    public GlobalResponseDto<ImageResponseDto> getByArticleId(Long articleId) {
        Optional<Image> image = imageRepository.findByArticleId(articleId);
        if (image.isEmpty()) {
            return null;
        }

        return GlobalResponseDto.of("200", "200", ImageResponseDto.builder()
                .storeFilename(image.get().getStoreFilename())
                .build());
    }

    //이미지 Path 얻기
    public String getFullPathStoreFilenameByArticleId(Long articleId) {
        String storeFilename = getByArticleId(articleId).getData().getStoreFilename();
        return fileStore.getFullPath(storeFilename);
    }
}
