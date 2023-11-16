package com.example.techit7.user.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.*;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String loginId;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String email;

}
