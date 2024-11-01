package com.gcms.v3.domain.auth.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash(value = "refreshToken", timeToLive = 60L * 60 * 24 * 7)
public class RefreshToken {

    @Indexed
    private String email;

    @Id
    @Indexed
    private String token;
}
