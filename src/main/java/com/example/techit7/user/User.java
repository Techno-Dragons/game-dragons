package com.example.techit7.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.*;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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
