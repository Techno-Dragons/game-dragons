package com.example.techit7.user.dto;

import com.example.techit7.user.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class mypageResponseDto {
    private String username;
    private String nickname;
    private String email;


    public mypageResponseDto(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
}
