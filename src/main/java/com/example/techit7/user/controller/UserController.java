package com.example.techit7.user.controller;

import com.example.techit7.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;




}
