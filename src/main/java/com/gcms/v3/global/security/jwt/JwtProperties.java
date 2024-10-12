package com.gcms.v3.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt.secret")
public record JwtProperties (
        String accessToken,
        String refreshToken
){
}


