package com.example.techit7.user.controller;

import com.example.techit7.global.config.JwtUtil;
import com.example.techit7.global.dto.GlobalResponse;
import com.example.techit7.user.dto.LoginRequestDto;
import com.example.techit7.user.dto.LoginResponseDto;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.entity.Member;
import com.example.techit7.user.service.MemberRestServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberRestController {

    private final MemberRestServiceImpl memberRestService;
    private final HttpServletResponse response;
    private final HttpServletRequest request;

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

    //TODO: 리프레시 토큰이 없는 경우, 멤버 객체를 찾을 수 없는 경우 예외처리 추가
    @PostMapping("/logout")
    public GlobalResponse logout(){
        //http request에서 refresh token 쿠키 추출
        Cookie refreshTokenCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst().get();

        // refresh token 쿠키 값을 이용해 멤버 객체 찾기
        Member member = memberRestService.findMemberByRefreshToken(refreshTokenCookie.getValue()).get();

        // 해당 멤버의 리프레시 토큰을 초기화해서 데이터베이스에 저장
        memberRestService.setRefreshToken(member,"");

        // response에 담아서 보낼 refresh token을 기한만료로 제거
        ResponseCookie cookie = ResponseCookie.from(refreshTokenCookie.getName(), null)
                .path("/")
                .maxAge(0)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        // access token은 따로 제거할 필요?

        return GlobalResponse.of("200","로그아웃 성공");
        //로그아웃 테스트는 일단 성공
    }


}
