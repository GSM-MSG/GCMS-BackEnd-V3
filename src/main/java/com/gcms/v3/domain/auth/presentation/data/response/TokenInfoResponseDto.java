package com.gcms.v3.domain.auth.presentation.data.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TokenInfoResponseDto(
        String accessToken,
        String refreshToken,
        LocalDateTime accessTokenExpiresIn,
        LocalDateTime refreshTokenExpiresIn
) {
}
