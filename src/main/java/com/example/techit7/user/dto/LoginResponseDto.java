package com.example.techit7.user.dto;

import com.example.techit7.user.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;
    private String nickname;

    public LoginResponseDto(Member member, String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.nickname = member.getNickname();
    }

}
