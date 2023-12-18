package com.example.techit7.user.controller;

import com.example.techit7.global.config.JwtUtil;
import com.example.techit7.global.dto.GlobalResponse;
import com.example.techit7.user.dto.LoginRequestDto;
import com.example.techit7.user.dto.LoginResponseDto;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.entity.Member;
import com.example.techit7.user.service.MemberRestServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberRestController {

    private final MemberRestServiceImpl memberRestService;
    private final HttpServletResponse response;

    @GetMapping("/test")
    public ResponseEntity<String> test1() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public GlobalResponse signup(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        Member member = memberRestService.signup(userCreateRequestDto).getData();
        return GlobalResponse.of("200","회원가입 완료.");
    }

    @PostMapping("/login")
    public GlobalResponse<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        GlobalResponse<Member> checkedResp = memberRestService.checkUsernameAndPassword(dto.getUsername(), dto.getPassword());

        Member member = checkedResp.getData();
        String accessToken = JwtUtil.encode(
                60 * 10, // 1 minute
                Map.of(
                        "id", member.getId().toString(),
                        "username", member.getUsername(),
                        "authorities", member.getAuthoritiesAsStrList()
                )
        );
        String refreshToken = JwtUtil.encode(
                60 * 60 * 24, //1 day
                Map.of(
                        "id", member.getId().toString(),
                        "username", member.getUsername()
                )
        );
        memberRestService.setRefreshToken(member, refreshToken);

        // TODO: 중복된 코드, 리팩토링 필요?
        ResponseCookie cookie1 = ResponseCookie.from("accessToken",accessToken)
                .httpOnly(true)
                .path("/")
                .sameSite("None")
                .secure(true)
                .build();

        ResponseCookie cookie2 = ResponseCookie.from("refreshToken",refreshToken)
                .httpOnly(true)
                .path("/")
                .sameSite("None")
                .secure(true)
                .build();

        response.addHeader("Set-Cookie",cookie1.toString());
        response.addHeader("Set-Cookie",cookie2.toString());

        return GlobalResponse.of("200","로그인 성공.",new LoginResponseDto(member,accessToken,refreshToken));
    }


}
