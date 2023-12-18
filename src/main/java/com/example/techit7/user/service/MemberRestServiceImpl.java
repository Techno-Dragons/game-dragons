package com.example.techit7.user.service;


import com.example.techit7.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRestServiceImpl {

    private final UserRepository userRepository;


}
