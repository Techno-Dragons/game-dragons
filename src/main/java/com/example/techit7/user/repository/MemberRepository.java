package com.example.techit7.user.repository;

import com.example.techit7.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByUsername(String username);

    Optional<Member> findByRefreshToken(String refreshToken);
}
