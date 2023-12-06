package com.example.techit7.comment.controller;

import com.example.techit7.article.entity.Article;
import com.example.techit7.article.service.ArticleServiceImpl;
import com.example.techit7.comment.dto.CommentRequestDto;
import com.example.techit7.comment.service.CommentServiceImpl;
import com.example.techit7.user.User;
import com.example.techit7.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;
    private final ArticleServiceImpl articleServiceImpl;
    private final UserServiceImpl userServiceImpl;

    //POST :/article/{articleId}/comment
    @PostMapping("/article/{articleId}/comment")
    public String write(Model model, @PathVariable("articleId") Long articleId,
                        @Valid CommentRequestDto req, BindingResult bindingResult, Principal principal) {
        Article article = articleServiceImpl.getArticleById(articleId);
        User user = userServiceImpl.getUserByUsername(principal.getName());

        if (bindingResult.hasErrors()){
            model.addAttribute("article", article);
        }

        model.addAttribute("commentResponse", commentServiceImpl.post(user, article, req));
        return "article/article_detail";
    }

    //PUT :/article/{articleId}/comment/{commentId}
    @PutMapping("/article/{articleId}/comment/{commentId}")
    public String modify(){
        // todo: 내용 구현
        return "article/article_detail";
    }

    //DELETE :/article/{articleId}/comment/{commentId}
    @DeleteMapping("/article/{articleId}/comment/{commentId}")
    public String delete(){
        // todo: 내용 구현
        return "article/article_detail";
    }
}
