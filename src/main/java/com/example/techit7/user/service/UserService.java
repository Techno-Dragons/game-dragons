package com.example.techit7.user.service;

import com.example.techit7.user.dto.UserRequestDto;
import com.example.techit7.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    /**
     * 유저 조회
     * @PARAM Long id
     * @RETURN User
     */
    User getUserById(Long id);

    /**
     * 유저 정보 변경
     * @PARAM Long id
     * @RETURN X
     */
    void updateUserById(Long id);

    /**
     * 회원가입
     * @PARAM userRequestDto
     * @RETURN X
     */
    boolean postUser(UserRequestDto userRequestDto);

    /**
     * 로그인
     * @PARAM UserRequestDto
     * @RETURN X
     */
    boolean login(UserRequestDto userRequestDto, HttpServletRequest request, HttpServletResponse response);
    
}
