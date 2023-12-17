package com.example.techit7.article.controller;//package com.example.techit7.article.controller;//package com.example.techit7.article.controller;
//
//

import com.example.techit7.article.dto.ArticleDetailResponseDto;
import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.dto.ArticleResponseDto;
import com.example.techit7.article.entity.Article;
import com.example.techit7.article.entity.Image;
import com.example.techit7.article.service.ArticleService;
import com.example.techit7.article.service.ImageService;
import com.example.techit7.global.dto.GlobalResponse;
import com.example.techit7.user.service.UserService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//
@RestController
@RequiredArgsConstructor
public class ArticleControllerRest {

    private final ArticleService articleService;
    private final ImageService imageService;
    private final UserService userService;

    // Article 전체 출력
    @RequestMapping(value = "/article", method = {RequestMethod.GET, RequestMethod.POST})
    public GlobalResponse articleAll(@RequestPart ArticleRequestDto articleRequestDto,
                                     @RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam String mode,
                                     Principal principal) throws IOException {

        if (mode.equals("write")) {
            Long articleId = articleService.postArticle(articleRequestDto,
                    userService.findByUsername(principal.getName()));
            imageService.save(multipartFile, articleId);
            return GlobalResponse.of("200", "wrtie success");
        }
        Page<Article> articles = articleService.getArticles(page);
        return GlobalResponse.of("200", "paging success", articles);
    }

//    // Article 저장
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/article")
//    public void createArticle(@RequestPart ArticleRequestDto articleRequestDto,
//                              @RequestPart(value = "file", required = false) MultipartFile multipartFile,
//                              Principal principal) throws IOException {
//
//        Long articleId = articleService.postArticle(articleRequestDto, null);
//
//        imageService.save(multipartFile, articleId);
//    }

    // Article 단일 출력
    @RequestMapping(value = "/article/{id}", method = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.DELETE})
    public GlobalResponse detailArticle(@PathVariable Long id,
                                        @RequestParam String mode,
                                        @RequestBody ArticleRequestDto articleRequestDto) {
        if (mode.equals("modify")) {
            articleService.updateArticleById(id, articleRequestDto);
            return GlobalResponse.of("200", "modify success");
        }
        if (mode.equals("delete")) {
            articleService.deleteArticleById(id);
            return GlobalResponse.of("200", "delete success");
        }

        Article article = articleService.findArticleById(id);
        Image image = imageService.getByArticleId(id);

        return GlobalResponse.of("200", "success", ArticleDetailResponseDto.builder()
                .article(article)
                .image(image));
    }

//    // Article 삭제
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/article/{id}")
//    public void deleteArticle(@PathVariable Long id) {
//        imageService.delete(id);
//        articleService.deleteArticleById(id);
//    }
//
//    // Article 수정
//    @ResponseStatus(HttpStatus.OK)
//    @PatchMapping("/article/{id}")
//    public void modifyArticle(@PathVariable Long id,
//                              @RequestBody ArticleRequestDto articleRequestDto) {
//
//        articleService.updateArticleById(id, articleRequestDto);
//    }


    // Image 출력
    @GetMapping("/article/image")
    public ResponseEntity<Resource> imageView(@RequestParam Long articleId) throws MalformedURLException {
        String fullPathFilename = imageService.getFullPathStoreFilenameByArticleId(articleId);

        return ResponseEntity.ok(new UrlResource("file:" + fullPathFilename));
    }

}
