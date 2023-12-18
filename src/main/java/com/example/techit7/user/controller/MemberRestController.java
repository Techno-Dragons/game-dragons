package com.example.techit7.user.controller;

import com.example.techit7.global.dto.GlobalResponse;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.service.MemberRestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberRestController {

    private final MemberRestServiceImpl memberRestService;

    @GetMapping("/test")
    public ResponseEntity<String> test1() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse> signup(@RequestBody UserCreateRequestDto userCreateRequestDto) {

    }


}
