package com.example.techit7.user.service;


import com.example.techit7.global.dto.GlobalResponseDto;
import com.example.techit7.user.dto.UserCreateRequestDto;
import com.example.techit7.user.dto.UserCreateResponseDto;
import com.example.techit7.user.entity.SiteUser;
import com.example.techit7.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public SiteUser getUserById(Long id) {
        return null;
    }

    @Override
    public void updateUserById(Long id) {

    }

    @Override
    public void login() {

    }

    @Override
    public GlobalResponseDto postUser(UserCreateRequestDto userCreateRequestDto){
        SiteUser siteUser = SiteUser.builder()
                .username(userCreateRequestDto.getUsername())
                .password(userCreateRequestDto.getPassword1())
                .nickname(userCreateRequestDto.getNickname())
                .email(userCreateRequestDto.getEmail())
                .build();

        userRepository.save(siteUser);
        return UserCreateResponseDto.of("200","회원가입 성공");
    }


    @Override
    public SiteUser findByUsername(String username) {
        Optional<SiteUser> siteUserOp = userRepository.findByUsername(username);
        if(!siteUserOp.isPresent()){
            throw new NullPointerException("일치하는 유저가 없습니다.");
        }
        // comment domain과 리턴값 협의 필요
        return siteUserOp.get();

    }



}
