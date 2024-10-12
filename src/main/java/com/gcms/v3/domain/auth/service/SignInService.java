package com.gcms.v3.domain.auth.service;

import com.gcms.v3.domain.auth.presentation.data.request.SignInRequestDto;
import com.gcms.v3.domain.auth.presentation.data.response.TokenInfoResponseDto;

public interface SignInService {
    TokenInfoResponseDto execute(SignInRequestDto signInRequestDto);
}
