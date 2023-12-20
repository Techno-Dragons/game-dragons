package com.example.techit7.user.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String email;
    private String nickname;
    private String refreshToken;

    @SuppressWarnings("JpaAttributeTypeInspection")
    public List<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesAsStrList()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @SuppressWarnings("JpaAttributeTypeInspection")
    public List<String> getAuthoritiesAsStrList() {
        return List.of("ROLE_MEMBER");
    }
}