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
     * 유저 패스워드 변경
     * @PARAM String username, userCreateRequestDto
     * @RETURN X
     */

    GlobalResponseDto updateUserPassword(String username, UserCreateRequestDto userCreateRequestDto);

    /**
     * 유저 닉네임,이메일 변경
     * @PARAM Long id
     * @RETURN X
     */

    GlobalResponseDto updateUser(String username, UserCreateRequestDto userCreateRequestDto);

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
