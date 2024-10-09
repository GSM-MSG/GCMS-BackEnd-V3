package com.gcms.v3.domain.user.domain.repository;

import com.gcms.v3.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, byte[]> {
    Optional<User> findByEmail(String email);
}
