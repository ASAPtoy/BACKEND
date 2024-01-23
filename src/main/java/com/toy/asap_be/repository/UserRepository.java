package com.toy.asap_be.repository;

import com.toy.asap_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 닉네임 존재 여부
    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);

    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);
}