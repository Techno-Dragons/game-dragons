package com.example.techit7.user.service;


import com.example.techit7.global.config.JwtUtil;
import com.example.techit7.global.config.SecurityUser;
import com.example.techit7.global.response.GlobalResponse;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.entity.Member;
import com.example.techit7.user.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberRestServiceImpl {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;


    public GlobalResponse signup(UserCreateRequestDto dto) {
        if (!validDuplicationUsername(dto.getUsername())) {
            return GlobalResponse.of("409", "중복된 이름입니다.");
        }
        Member member = Member.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword2()))
                .nickname(dto.getNickname())
                .email(dto.getEmail()).build();

        memberRepository.save(member);
        return GlobalResponse.of("200", "회원가입 완료");
    }

    public boolean validDuplicationUsername(String username) {
        return memberRepository.findByUsername(username).isPresent() ? false : true;
    }

    public GlobalResponse<Member> checkUsernameAndPassword(String username, String password) {
        Optional<Member> memberOp = memberRepository.findByUsername(username);

        if (memberOp.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        if (!encoder.matches(password, memberOp.get().getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return GlobalResponse.of("200", "로그인 성공", memberOp.get());
    }

    public void setRefreshToken(Member member, String refreshToken) {
        memberRepository.save(member.toBuilder().refreshToken(refreshToken).build());
    }

    public Optional<Member> findMemberByRefreshToken(String refreshToken) {
        return memberRepository.findByRefreshToken(refreshToken);
    }

    public SecurityUser getUserFromApiKey(String apiKey) {
        Claims claims = JwtUtil.decode(apiKey);

        Map<String, Object> data = (Map<String, Object>) claims.get("data");
        long id = Long.parseLong((String) data.get("id"));
        String username = (String) data.get("username");
        List<? extends GrantedAuthority> authorities = ((List<String>) data.get("authorities"))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new SecurityUser(
                id,
                username,
                "",
                authorities
        );
    }

}
