package com.example.techit7.comment.controller;

import com.example.techit7.article.entity.Article;
import com.example.techit7.article.service.ArticleServiceImpl;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.service.CommentRestServiceImpl;
import com.example.techit7.global.response.GlobalResponse;
import com.example.techit7.user.entity.Member;
import com.example.techit7.user.service.MemberRestServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/article/{articleId}/comment")
@RequiredArgsConstructor
public class CommentRestController {
    private final CommentRestServiceImpl commentService;
    private final ArticleServiceImpl articleService;
    private final MemberRestServiceImpl memberRestService;

    //POST :/article/{articleId}/comment
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public GlobalResponse write(
            @PathVariable("articleId") Long articleId,
            @RequestBody @Valid CommentRequestDto req,
            BindingResult bindingResult,
            Principal principal
    ) {
        Article article = articleService.findArticleById(articleId);
        Member member = memberRestService.findByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            return GlobalResponse.of("400", "error", bindingResult.getAllErrors());
        }

        return GlobalResponse.of("200", "success", commentService.post(member, article, req));
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
        Member member = memberRestService.findByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            return GlobalResponse.of("400", "error", bindingResult.getAllErrors());
        }

        return GlobalResponse.of("200", "success", commentService.update(member, commentId, req));
    }

    //DELETE :/article/{articleId}/comment/{commentId}
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public GlobalResponse delete(
            @PathVariable("commentId") Long commentId,
            Principal principal
    ) {
        Member member = memberRestService.findByUsername(principal.getName());

        commentService.delete(member, commentId);
        return GlobalResponse.of("200", "success");
    }
}
