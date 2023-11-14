package com.example.techit7.user;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void updateUserById(Long id) {

    }

    @Override
    public void postUser(Long id, String password, String nickname, String email) {

    }
}
