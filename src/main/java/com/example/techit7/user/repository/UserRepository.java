package com.example.techit7.user.repository;

import com.example.techit7.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long> {

    Optional<SiteUser> findByNickname(String nickName);

    // Optional<SiteUser> findByLoginId(String loginId);

    Optional<SiteUser> findByPassword(String password);

    @Transactional(readOnly = true)
    Optional<SiteUser> findByUsername(String username);

    Optional<SiteUser> findByEmail(String email);

}
