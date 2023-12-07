package com.example.techit7.article.controller;


import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.dto.ArticleResponseDto;
import com.example.techit7.article.service.ArticleServiceImpl;
import com.example.techit7.article.service.ImageService;
import com.example.techit7.global.dto.GlobalResponseDto;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleServiceImpl articleService;
    private final ImageService imageService;

    // Article 전체 출력
    @GetMapping("/article")
    public String articleAll(ArticleResponseDto articleResponseDto,
                             @RequestBody(required = false) ArticleRequestDto articleRequestDto,
                             @RequestParam String mode,
                             Model model) {

        if (mode.equals("write")) {
            return "writeArticleForm";
        }
        List<GlobalResponseDto<ArticleResponseDto>> articleResponseDtos = articleService.getArticles();
        model.addAttribute(articleResponseDtos);

        return "listArticles";
    }

    // Article 저장
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/article")
    public String createArticle(@ModelAttribute ArticleRequestDto articleRequestDto,
                                Principal principal) throws IOException {

        Long articleId = articleService.postArticle(articleRequestDto, null);

        imageService.save(articleRequestDto.getMultipartFile(), articleId);
        return "redirect:/article";
    }

    // Article 단일 출력
    @GetMapping("/article/{id}")
    public String detailArticle(@PathVariable Long id,
                                @RequestParam String mode,
                                ArticleRequestDto articleRequestDto) {

        GlobalResponseDto<ArticleResponseDto> articleResponseDto = articleService.getArticleById(id);

        if (mode.equals("modify")) {
            articleService.updateArticleById(id, articleRequestDto);
            return "modifyForm";
        }
        if (mode.equals("delete")) {
            imageService.delete(id);
            articleService.deleteArticleById(id);
            return "redirect:/";
        }

        return "detailForm";
    }

    // Article 수정
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/article/{id}")
    public void modifyArticle(@PathVariable Long id,
                              ArticleRequestDto articleRequestDto) throws IOException {

        articleService.updateArticleById(id, articleRequestDto);
        imageService.update(articleRequestDto.getMultipartFile(), id);
    }

    // Image 출력
    @GetMapping("/article/image/")
    public ResponseEntity<Resource> imageView(@RequestParam Long articleId) throws MalformedURLException {
        String fullPathFilename = imageService.getFullPathStoreFilenameByArticleId(articleId);

        return ResponseEntity.ok(new UrlResource("file:" + fullPathFilename));
    }

}
