package com.example.techit7.user.service;

import com.example.techit7.user.dto.UserRequestDto;
import com.example.techit7.user.entity.User;

public interface UserService {

    /**
     * 아이디로 유저 조회
     * @PARAM Long id
     * @RETURN User
     */
    User getUserById(Long id);

    /**
     * 아이디로 유저 정보 변경
     * @PARAM Long id
     * @RETURN X
     */
    void updateUserById(Long id);

    /**
     * 회원가입?
     * @PARAM Long id
     * @RETURN X
     */
    void postUser(UserRequestDto userRequestDto);

    
}
