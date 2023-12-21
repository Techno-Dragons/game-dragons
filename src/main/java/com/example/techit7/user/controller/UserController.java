package com.example.techit7.user.controller;

import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.entity.SiteUser;
import com.example.techit7.user.service.UserService;
import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login_form";
    }

    @GetMapping("/signup")
    public String signup(UserCreateRequestDto userCreateRequestDto) {
        return "user/signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateRequestDto userCreateRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/signup_form";
        }

        if (!userCreateRequestDto.getPassword1().equals(userCreateRequestDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "user/signup_form";
        }
        try {
            userService.postUser(userCreateRequestDto);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "user/signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "user/signup_form";
        }

        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String mypage(UserCreateRequestDto userCreateRequestDto, Principal principal, Model model) {
        try {
            String loginedUsername = principal.getName();
            SiteUser user = userService.findByUsername(loginedUsername);
            model.addAttribute("user", user);
        } catch (Exception e) {
            return "redirect:/";
        }
        return "user/mypage_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/mypage/modify")
    public String modify(UserCreateRequestDto dto, Principal principal, BindingResult bindingResult) {
        userService.updateUser(principal.getName(), dto);
        return "redirect:/member/mypage";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/mypage/modifyPassword")
    public String modifyPassword(UserCreateRequestDto dto, Principal principal, BindingResult bindingResult) {
        if (!dto.getPassword1().equals(dto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "redirect:/member/mypage";
        }
        userService.updateUserPassword(principal.getName(), dto);

        return "redirect:/member/mypage";
    }


}
