package com.example.techit7.user.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column(unique = true)
    private String email;

}
