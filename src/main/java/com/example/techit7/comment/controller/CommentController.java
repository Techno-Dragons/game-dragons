package com.example.techit7.comment.controller;

import com.example.techit7.article.entity.Article;
import com.example.techit7.article.service.ArticleServiceImpl;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.service.CommentServiceImpl;
import com.example.techit7.global.response.GlobalResponse;
import com.example.techit7.user.entity.SiteUser;
import com.example.techit7.user.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/article/{articleId}/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;
    private final ArticleServiceImpl articleServiceImpl;
    private final UserServiceImpl userServiceImpl;

    //POST :/article/{articleId}/comment
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public GlobalResponse write(
            @PathVariable("articleId") Long articleId,
            @RequestBody @Valid CommentRequestDto req,
            BindingResult bindingResult,
            Principal principal
    ) {
        Article article = articleServiceImpl.findArticleById(articleId);
        SiteUser siteUser = userServiceImpl.findByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            return GlobalResponse.of("400", "error", bindingResult.getAllErrors());
        }

        return GlobalResponse.of("200", "success", commentServiceImpl.post(siteUser, article, req));
    }

    //PUT :/article/{articleId}/comment/{commentId}
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{commentId}")
    public GlobalResponse modify(
            @PathVariable("commentId") Long commentId,
            @RequestBody @Valid CommentRequestDto req,
            BindingResult bindingResult,
            Principal principal
    ) {
        SiteUser siteUser = userServiceImpl.findByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            return GlobalResponse.of("400", "error", bindingResult.getAllErrors());
        }

        return GlobalResponse.of("200", "success", commentServiceImpl.update(siteUser, commentId, req));
    }

    //DELETE :/article/{articleId}/comment/{commentId}
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public GlobalResponse delete(
            @PathVariable("commentId") Long commentId,
            Principal principal
    ) {
        SiteUser siteUser = userServiceImpl.findByUsername(principal.getName());

        commentServiceImpl.delete(siteUser, commentId);
        return GlobalResponse.of("200", "success");
    }
}
