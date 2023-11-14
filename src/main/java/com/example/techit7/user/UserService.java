package com.example.techit7.user;

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
    void postUser(Long id, String password, String nickname, String email);

    
}
