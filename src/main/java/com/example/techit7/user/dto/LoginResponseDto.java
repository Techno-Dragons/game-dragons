package com.example.techit7.user.dto;

import com.example.techit7.user.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponseDto {
    private String nickname;
    private String username;

    public LoginResponseDto(Member member){
        this.nickname = member.getNickname();
        this.username = member.getUsername();
    }
}
