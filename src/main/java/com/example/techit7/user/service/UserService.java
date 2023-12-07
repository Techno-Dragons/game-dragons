package com.example.techit7.user.service;

import com.example.techit7.user.entity.SiteUser;
import com.example.techit7.user.dto.UserCreateForm;

import java.util.Optional;

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
     * @PARAM userRequestDto
     * @RETURN X
     */
    void postUser(UserCreateForm userCreateForm);

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
