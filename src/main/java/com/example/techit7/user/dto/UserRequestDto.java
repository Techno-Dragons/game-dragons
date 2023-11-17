package com.example.techit7.user.dto;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String loginId;
    private String password;
    private String nickName;
    private String email;

    public boolean isDtoEntireVariableNull() {
        try {
            for (Field f : getClass().getDeclaredFields()) {
                if (f.get(this) != null) {
                    return false;
                }
            }
            return true;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
