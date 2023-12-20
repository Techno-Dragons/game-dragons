package com.example.techit7.global.config;

import com.example.techit7.user.service.MemberRestServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberRestServiceImpl memberService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        System.out.println("JwtAuthenticationFilter 실행");

        String apiKey = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            Optional<Cookie> rqCookie = Arrays.stream(cookies)
                            .filter(cookie -> cookie.getName().equals("accessToken"))
                            .findFirst();
            if(rqCookie.isPresent()){
                apiKey = rqCookie.get().getValue();
            }
        }

        if (apiKey != null) {
            SecurityUser user = memberService.getUserFromApiKey(apiKey);
            setAuthentication(user);
        }

        filterChain.doFilter(request, response);
    }

    public void setAuthentication(SecurityUser user) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user,
                user.getPassword(),
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
