package com.example.techit7.article.controller;//package com.example.techit7.article.controller;//package com.example.techit7.article.controller;
//
//
//import com.example.techit7.article.dto.ArticleRequestDto;
//import com.example.techit7.article.dto.ArticleResponseDto;
//import com.example.techit7.article.service.ArticleServiceImpl;
//import com.example.techit7.article.service.ImageService;
//import com.example.techit7.global.dto.GlobalResponseDto;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.security.Principal;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequiredArgsConstructor
//public class ArticleControllerRest {
//
//    private final ArticleServiceImpl articleService;
//    private final ImageService imageService;
//
//    // Article 전체 출력
//    @GetMapping("/article")
//    public ResponseEntity<List<GlobalResponseDto<ArticleResponseDto>>> articleAll(ArticleResponseDto articleResponseDto
////                                                               @RequestBody(required = false) ArticleRequestDto articleRequestDto,
////                                                               @RequestParam String mode
//    ) {
////        if (mode.equals("write")) {
////
////        }
//        List<GlobalResponseDto<ArticleResponseDto>> articleResponseDtos = articleService.getArticles();
//        return ResponseEntity.ok(articleResponseDtos);
//    }
//
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
//
//    // Article 단일 출력
//    @GetMapping("/article/{id}")
//    public ResponseEntity<GlobalResponseDto<ArticleResponseDto>> detailArticle(@PathVariable Long id,
//                                                            @RequestParam String mode,
//                                                            ArticleRequestDto articleRequestDto) {
////        if (mode.equals("modify")) {
////            articleService.updateArticleById(id, articleRequestDto);
////        }
////        if (mode.equals("delete")) {
////            articleService.deleteArticleById(id);
////            return ResponseEntity.noContent()
////        }
////
//        GlobalResponseDto<ArticleResponseDto> articleResponseDto = articleService.getArticleById(id);
//
//        return ResponseEntity.ok(articleResponseDto);
//    }
//
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
//
//
//    // Image 출력
//    @GetMapping("/article/image")
//    public ResponseEntity<Resource> imageView(@RequestParam Long articleId) throws MalformedURLException {
//        String fullPathFilename = imageService.getFullPathStoreFilenameByArticleId(articleId);
//
//        return ResponseEntity.ok(new UrlResource("file:" + fullPathFilename));
//    }
//
//}
