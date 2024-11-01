package com.gcms.v3.domain.auth.domain.repository;

import com.gcms.v3.domain.auth.domain.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken findByEmail(String email);
}
