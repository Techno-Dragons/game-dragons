package com.example.techit7.user.service;

import com.example.techit7.global.dto.GlobalResponseDto;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.entity.SiteUser;

public interface UserService {

    /**
     * 유저 조회
     * @PARAM Long id
     * @RETURN SiteUser
     */
    SiteUser getUserById(Long id);

    /**
     * 유저 정보 변경
     * @PARAM Long id
     * @RETURN X
     */
    void updateUserById(Long id);

    /**
     * 회원가입
     *
     * @return
     * @PARAM userRequestDto
     * @RETURN X
     */
    GlobalResponseDto postUser(UserCreateRequestDto userCreateRequestDto);

    /**
     * 로그인
     * @PARAM UserRequestDto
     * @RETURN X
     */
    void login();

    /**
     * 유저 찾기
     * @PARAM String username
     * @RETURN SiteUser
     */
    SiteUser findByUsername(String username);

}
