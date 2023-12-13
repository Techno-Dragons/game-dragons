package com.example.techit7.article.controller;


import com.example.techit7.article.dto.ArticleRequestDto;
import com.example.techit7.article.dto.ArticleResponseDto;
import com.example.techit7.article.dto.ImageResponseDto;
import com.example.techit7.article.service.ArticleServiceImpl;
import com.example.techit7.article.service.ImageService;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.dto.CommentResponseDto;
import com.example.techit7.comment.entity.Comment;
import com.example.techit7.comment.service.CommentServiceImpl;
import com.example.techit7.global.dto.GlobalResponseDto;
import com.example.techit7.user.repository.UserRepository;
import com.example.techit7.user.service.UserServiceImpl;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleServiceImpl articleService;
    private final ImageService imageService;
    private final UserServiceImpl userService;
    private final CommentServiceImpl commentService;

    @GetMapping("/")
    public String articleHome() {
        return "redirect:/article";
    }

    // Article 전체 출력
    @GetMapping("/article")
    public String articleAll(@ModelAttribute ArticleRequestDto articleRequestDto,
                             @RequestParam(defaultValue = "") String mode,
                             @RequestParam(defaultValue = "0") Integer page,
                             Model model) {

        if (mode.equals("write")) {
            return "article/article_form";
        }
        GlobalResponseDto<Page<ArticleResponseDto>> articleResponseDtos = articleService.getArticles(page);
        model.addAttribute("paging", articleResponseDtos.getData());

        return "article/articles";
    }

    // Article 저장
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/article")
    public String createArticle(@ModelAttribute ArticleRequestDto articleRequestDto,
                                Principal principal) throws IOException {



        Long articleId = articleService.postArticle(articleRequestDto, userService.findByUsername(principal.getName()));

        imageService.save(articleRequestDto.getMultipartFile(), articleId);
        return String.format("redirect:/article/%d", articleId);
    }


    // Article 단일 출력
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/article/{id}")
    public String detailArticle(@PathVariable Long id,
                                @RequestParam(defaultValue = "") String mode,
                                @ModelAttribute("articleRequestDto") ArticleRequestDto articleRequestDto,
                                @ModelAttribute("commentRequestDto") CommentRequestDto commentRequestDto,
                                Principal principal,
                                Model model) {

        GlobalResponseDto<ArticleResponseDto> articleResponseDto = articleService.getArticleById(id);
        model.addAttribute("articleResponseDto", articleResponseDto);

        GlobalResponseDto<ImageResponseDto> imageResponseDto = imageService.getByArticleId(id);
        model.addAttribute("imageResponseDto", imageResponseDto);

        if (mode.equals("modify")) {
            return "article/article_modify_form";
        }
        if (mode.equals("delete")) {
            imageService.delete(id);
            articleService.deleteArticleById(id);
            return "redirect:/";
        }

        return "article/article_detail";
    }

    // Article 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/article/{id}")
    public String modifyArticle(@PathVariable Long id,
                              ArticleRequestDto articleRequestDto,
                              CommentResponseDto commentResponseDto,
                              Principal principal) throws IOException {

        articleService.updateArticleById(id, articleRequestDto);
        imageService.update(articleRequestDto.getMultipartFile(), id);
        //TODO 수정 view 구현 필요

        return "redirect:/article/{id}";
    }

    // Image 출력
    @GetMapping("/article/image")
    public ResponseEntity<Resource> imageView(@RequestParam Long articleId) throws MalformedURLException {
        String fullPathFilename = imageService.getFullPathStoreFilenameByArticleId(articleId);

        return ResponseEntity.ok(new UrlResource("file:" + fullPathFilename));
    }

}
