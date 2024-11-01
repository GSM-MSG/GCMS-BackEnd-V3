package com.gcms.v3.domain.auth.service;

import com.gcms.v3.domain.auth.presentation.data.response.TokenInfoResponseDto;

public interface ReissueTokenService {
    TokenInfoResponseDto execute(String refreshToken);
}
