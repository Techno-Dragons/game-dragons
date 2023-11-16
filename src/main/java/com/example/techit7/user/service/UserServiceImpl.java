package com.example.techit7.user.service;

import com.example.techit7.global.exception.DuplicateNicknameException;
import com.example.techit7.user.dto.UserRequestDto;
import com.example.techit7.user.entity.User;
import com.example.techit7.user.repository.UserRepository;
import com.example.techit7.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void updateUserById(Long id) {

    }

    @Override
    public void postUser(UserRequestDto userRequestDto) {
        //닉네임 중복체크
        userRepository.findByNickname(userRequestDto.getNickName()).ifPresent(user -> {
            throw new RuntimeException();
        });

        // 아이디 중복체크
        userRepository.findByLoginId(userRequestDto.getLoginId()).ifPresent(user -> {
            throw new RuntimeException();
        });

        // 이메일 인증 (추후 확장)

        // 저장
        User user = userRepository.save(
                User.builder()
                        .loginId(userRequestDto.getLoginId())
                        .password(userRequestDto.getPassword())
                        .nickname(userRequestDto.getNickName())
                        .email(userRequestDto.getEmail())
                        .build());
    }
}
