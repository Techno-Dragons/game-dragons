package com.example.techit7.article.controller;//package com.example.techit7.article.controller;//package com.example.techit7.article.controller;
//
//

import com.example.techit7.article.dto.ArticleDetailResponseDto;
import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.entity.Article;
import com.example.techit7.article.entity.Image;
import com.example.techit7.article.service.ArticleService;
import com.example.techit7.article.service.ImageService;
import com.example.techit7.global.response.GlobalResponse;
import com.example.techit7.user.service.MemberRestServiceImpl;
import jakarta.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//
@RestController
@RequiredArgsConstructor
public class ArticleControllerRest {

    private final ArticleService articleService;
    private final ImageService imageService;
    private final MemberRestServiceImpl memberRestService;

    @GetMapping("/article")
    public GlobalResponse articleAll(@RequestParam(defaultValue = "0") int page) {

        Page<Article> articles = articleService.getArticles(page);
        return GlobalResponse.of("200", "paging success", articles);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/article")
    public GlobalResponse createArticle(@Valid @RequestPart ArticleRequestDto articleRequestDto,
                                        @RequestPart(required = false, name = "imageFile") MultipartFile imageFile,
                                        Principal principal) throws IOException {

        Long articleId = articleService.postArticle(articleRequestDto,
                memberRestService.findByUsername(principal.getName()));
        imageService.save(imageFile, articleId);

        return GlobalResponse.of("200", "create success", articleId);
    }


    //단일 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/article/{id}")
    public GlobalResponse detailArticle(@PathVariable Long id) {

        Article article = articleService.findArticleById(id);
        Image image = imageService.getByArticleId(id);

        return GlobalResponse.of("200", "success", ArticleDetailResponseDto.builder()
                .article(article)
                .image(image).build());
    }

    // Article 수정
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/article/{id}")
    public GlobalResponse modifyArticle(@PathVariable Long id,
                                        @Valid @RequestPart ArticleRequestDto articleRequestDto,
                                        @RequestPart(required = false, name = "imageFile") MultipartFile imageFile,
                                        Principal principal) throws IOException {

        articleService.updateArticleById(id, articleRequestDto, principal.getName());
        imageService.update(imageFile, id, principal.getName());
         return GlobalResponse.of("200", "modify success");
    }

    // Article 삭제
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/article/{id}")
    public GlobalResponse deleteArticle(@PathVariable Long id,
                                        Principal principal) {

        imageService.delete(id, principal.getName());
        articleService.deleteArticleById(id, principal.getName());
        return GlobalResponse.of("200", "delete success");
    }


    // Image 출력
    @GetMapping("/article/image")
    public ResponseEntity<Resource> imageView(@RequestParam Long articleId) throws MalformedURLException {
        String fullPathFilename = imageService.getFullPathStoreFilenameByArticleId(articleId);

        return ResponseEntity.ok(new UrlResource("file:" + fullPathFilename));
    }

}
