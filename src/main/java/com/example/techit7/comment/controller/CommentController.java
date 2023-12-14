package com.example.techit7.comment.controller;

import com.example.techit7.article.entity.Article;
import com.example.techit7.article.service.ArticleServiceImpl;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.service.CommentServiceImpl;
import com.example.techit7.user.entity.SiteUser;
import com.example.techit7.user.service.UserServiceImpl;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article/{articleId}/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;
    private final ArticleServiceImpl articleServiceImpl;
    private final UserServiceImpl userServiceImpl;

    //POST :/article/{articleId}/comment
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public String write(
            Model model,
            @PathVariable("articleId") Long articleId,
            @Valid CommentRequestDto req,
            BindingResult bindingResult,
            Principal principal
    ) {
        Article article = articleServiceImpl.findArticleById(articleId);
        SiteUser siteUser = userServiceImpl.findByUsername(principal.getName());

        if (bindingResult.hasErrors()){
            model.addAttribute("article", article);
        }

        model.addAttribute("commentResponse", commentServiceImpl.post(siteUser, article, req));

        return "redirect:/article/{articleId}";
    }

    //PUT :/article/{articleId}/comment/{commentId}
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{commentId}")
    public String modify(
            @PathVariable("articleId") Long articleId,
            @PathVariable("commentId") Long commentId,
            @Valid CommentRequestDto req,
            BindingResult bindingResult,
            Principal principal,
            Model model
    ){
        Article article = articleServiceImpl.findArticleById(articleId);
        SiteUser siteUser = userServiceImpl.findByUsername(principal.getName());

        if(bindingResult.hasErrors()){
            model.addAttribute("article", article);
        }
        model.addAttribute("commentResponse", commentServiceImpl.update(siteUser, commentId, req));
        return "redirect:/article/{articleId}";
    }

    //DELETE :/article/{articleId}/comment/{commentId}
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public String delete(
            @PathVariable("commentId") Long commentId,
            Principal principal,
            Model model
    ) {
        SiteUser siteUser = userServiceImpl.findByUsername(principal.getName());

        model.addAttribute("commentResponse", commentServiceImpl.delete(siteUser, commentId));
        return "redirect:/article/{articleId}";
    }
}
