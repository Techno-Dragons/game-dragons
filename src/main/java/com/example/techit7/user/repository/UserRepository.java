package com.example.techit7.user.repository;

import com.example.techit7.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickName);

    Optional<User> findByLoginId(String loginId);

    Optional<User> findByPassword(String password);


}
