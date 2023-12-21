package com.example.techit7.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestTest {

    private String username;
    private String password1;
    private String password2;
    private String email;
    private String nickname;
}
