package com.example.techit7.user.controller;

import com.example.techit7.global.dto.GlobalResponse;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test1() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/test/signup")
    public ResponseEntity<GlobalResponse> signup(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        GlobalResponse result = userService.postUser(userCreateRequestDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//    @PostMapping("/test/signup")
//    public ResponseEntity<UserRequestTest> signup2(@RequestBody UserRequestTest userRequestTest) {
////        GlobalResponseDto result = userService.postUser(userCreateRequestDto);
//
//        return new ResponseEntity<>(userRequestTest, HttpStatus.OK);
//    }

//    json
//    @GetMapping("/response-body-json-v1")
//    public ResponseEntity<HelloData> responseBodyJsonV1() {
//        HelloData helloData = new HelloData();
//        helloData.setUsername("userA");
//        helloData.setAge(20);
//
//        return new ResponseEntity<>(helloData,HttpStatus.OK);
//    }


}
