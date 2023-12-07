package com.example.techit7.user.service;


import com.example.techit7.user.dto.UserCreateForm;
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
    public void postUser(UserCreateForm userCreateForm){
        SiteUser siteUser = SiteUser.builder()
                .username(userCreateForm.getUsername())
                .password(userCreateForm.getPassword1())
                .nickname(userCreateForm.getNickname())
                .email(userCreateForm.getEmail())
                .build();

        userRepository.save(siteUser);
    }

    @Override
    public SiteUser findByUsername(String username) {
        Optional<SiteUser> siteUserOp = userRepository.findByUsername(username);
        if(!siteUserOp.isPresent()){
            // 커스텀 예외 추가
            throw new NullPointerException("일치하는 유저가 없습니다.");
        }
        return siteUserOp.get();

    }


//    @Override
//    public boolean login(UserRequestDto userRequestDto, HttpServletRequest request, HttpServletResponse response){
//
//
//        // id, password 확인 과정
//        User user = userRepository.findByLoginId(userRequestDto.getLoginId())
//                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다."));
//
//        if(!encoder.matches(userRequestDto.getPassword(),user.getPassword())){
//            // 로그인 실패
//            return false;  // 추후에 예외 추가
//        }
//
//        // 쿠키 및 세션 설정
//        Cookie cookie = new Cookie("loginedUserId", user.getLoginId());
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
//        HttpSession session = request.getSession();
//        session.setAttribute("loginedUserId", user.getLoginId());
//        return true;
//    }
//    @Override
//    public boolean postUser(UserRequestDto userRequestDto) {
//        //  DTO값들의 널값 체크 필요
//        if(!userRequestDto.isDtoEntireVariableNull()){
//            return false;
//        }
//
//        //닉네임 중복체크
//        userRepository.findByNickname(userRequestDto.getNickName()).ifPresent(user -> {
//            throw new DuplicateException("닉네임 중복입니다.");
//        });
//
//        // 아이디 중복체크
//        userRepository.findByLoginId(userRequestDto.getLoginId()).ifPresent(user -> {
//            throw new DuplicateException("아이디 중복입니다.");
//        });
//
//        // 이메일 인증 (추후 추가)
//
//
//
//        // 저장
//        SiteUser user = userRepository.save(
//                SiteUser.builder()
//                        .username(userRequestDto.getLoginId())
//                        .password(encoder.encode(userRequestDto.getPassword()))  // 패스워드 암호화
//                        .nickname(userRequestDto.getNickName())
//                        .email(userRequestDto.getEmail())
//                        .build());
//
//        return true;
//    }
}
