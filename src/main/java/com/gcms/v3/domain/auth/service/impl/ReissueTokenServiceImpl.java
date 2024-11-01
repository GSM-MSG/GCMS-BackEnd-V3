package com.gcms.v3.domain.auth.service.impl;

import com.gcms.v3.domain.auth.domain.entity.RefreshToken;
import com.gcms.v3.domain.auth.domain.repository.RefreshTokenRepository;
import com.gcms.v3.domain.auth.exception.UserNotFoundException;
import com.gcms.v3.domain.auth.presentation.data.response.TokenInfoResponseDto;
import com.gcms.v3.domain.auth.service.ReissueTokenService;
import com.gcms.v3.domain.user.domain.entity.User;
import com.gcms.v3.domain.user.domain.repository.UserRepository;
import com.gcms.v3.global.security.exception.ExpiredTokenException;
import com.gcms.v3.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenInfoResponseDto execute(String refreshToken) {
        String refresh = jwtTokenProvider.parseToken(refreshToken);

        String email = jwtTokenProvider.exactEmailFromRefreshToken(refresh);

        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        RefreshToken existingRefreshToken = refreshTokenRepository.findByToken(refresh)
                .orElseThrow(ExpiredTokenException::new);

        TokenInfoResponseDto responseDto = jwtTokenProvider.generateToken(user.getEmail());

        saveRefreshToken(existingRefreshToken.getEmail(), responseDto.refreshToken());

        return responseDto;
    }

    private void saveRefreshToken(String email, String refreshToken) {
        RefreshToken token = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        refreshTokenRepository.save(token);
    }
}
