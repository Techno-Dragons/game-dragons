package com.example.techit7.user.service;


import com.example.techit7.global.dto.GlobalResponse;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.entity.Member;
import com.example.techit7.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberRestServiceImpl {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;


    public GlobalResponse<Member> signup(UserCreateRequestDto dto) {
        Member member = Member.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword2()))
                .nickname(dto.getNickname())
                .email(dto.getEmail()).build();

        memberRepository.save(member);
        return GlobalResponse.of("200","회원가입 완료", member);
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

    public void setRefreshToken(Member member, String refreshToken){
        memberRepository.save(member.toBuilder().refreshToken(refreshToken).build());
    }
}
