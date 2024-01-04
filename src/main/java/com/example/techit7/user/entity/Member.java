package com.example.techit7.user.entity;
import com.example.techit7.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
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