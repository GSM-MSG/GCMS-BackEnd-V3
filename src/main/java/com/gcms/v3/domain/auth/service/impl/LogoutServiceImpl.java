package com.gcms.v3.domain.auth.service.impl;

import com.gcms.v3.domain.auth.domain.entity.RefreshToken;
import com.gcms.v3.domain.auth.domain.repository.RefreshTokenRepository;
import com.gcms.v3.domain.auth.exception.UserNotFoundException;
import com.gcms.v3.domain.auth.service.LogoutService;
import com.gcms.v3.domain.user.domain.entity.User;
import com.gcms.v3.domain.user.util.UserUtil;
import com.gcms.v3.global.redis.RedisUtil;
import com.gcms.v3.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class LogoutServiceImpl implements LogoutService {

    private final UserUtil userUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtil redisUtil;

    public void execute(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.resolveToken(request);

        User user = userUtil.getCurrentUser();

        RefreshToken refreshToken = refreshTokenRepository.findByEmail(user.getEmail())
                .orElseThrow(UserNotFoundException::new);

        refreshTokenRepository.delete(refreshToken);

        redisUtil.setBlackList(accessToken, "access_token", jwtTokenProvider.getExpiration(accessToken));
    }
}
