package com.example.techit7.global.config;

import com.example.techit7.user.entity.SiteUser;
import com.example.techit7.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteUser member = userService.findByUsername(username);

        return new SecurityUser(
                member.getId(),
                member.getUsername() + "",
                member.getPassword(),
                member.getAuthorities()
        );
    }
}
