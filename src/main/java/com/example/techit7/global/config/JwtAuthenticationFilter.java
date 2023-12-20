package com.example.techit7.global.config;

import com.example.techit7.user.service.MemberRestServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberRestServiceImpl memberService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        System.out.println("JwtAuthenticationFilter 실행");

        String apiKey = request.getHeader("X-ApiKey");
        if (apiKey == null) {
            apiKey = null;
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
