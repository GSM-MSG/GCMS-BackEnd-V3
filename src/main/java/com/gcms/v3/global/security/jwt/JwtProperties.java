package com.gcms.v3.global.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {
    @Value("${jwt.secret.access-token}")
    private String accessTokenKey;

    @Value("${jwt.secret.refresh-token}")
    private String refreshTokenKey;

    public static final String AUTHORITIES = "auth";

    public static final String GRANT_TYPE = "Bearer";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final long ACCESS_TOKEN_TIME = 1000 * 60 * 30L;

    public static final long REFRESH_TOKEN_TIME = 1000L * 60 * 60 * 24 * 7;
}
