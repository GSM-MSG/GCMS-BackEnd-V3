package com.gcms.v3.domain.auth.service.impl;

import com.gcms.v3.domain.auth.presentation.data.request.SignInRequestDto;
import com.gcms.v3.domain.auth.presentation.data.response.TokenInfoResponseDto;
import com.gcms.v3.domain.auth.service.SignInService;
import com.gcms.v3.domain.user.domain.entity.User;
import com.gcms.v3.domain.user.domain.entity.UserRole;
import com.gcms.v3.domain.user.domain.enums.Authority;
import com.gcms.v3.domain.user.domain.repository.UserRepository;
import com.gcms.v3.domain.user.domain.repository.UserRoleRepository;
import com.gcms.v3.global.oauth2.GoogleOAuth2UserInfo;
import com.gcms.v3.global.oauth2.OAuth2Service;
import com.gcms.v3.global.security.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final OAuth2Service oAuth2Service;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRoleRepository userRoleRepository;

    public TokenInfoResponseDto execute(SignInRequestDto signInRequestDto) {
        String accessToken = oAuth2Service.requestAccessToken(signInRequestDto.getCode());
        GoogleOAuth2UserInfo googleOAuth2UserInfo = oAuth2Service.requestUserInfo(accessToken);

        User user = userRepository.findByEmail(googleOAuth2UserInfo.getEmail())
                .orElseGet(() -> toEntity(googleOAuth2UserInfo));

        return jwtTokenProvider.generateToken(user.getEmail());
    }

    private User toEntity(GoogleOAuth2UserInfo googleOAuth2UserInfo) {
        User user = User.builder()
                .name(googleOAuth2UserInfo.getName())
                .email(googleOAuth2UserInfo.getEmail())
                .build();

        userRepository.save(user);
        saveAuthority(user);
        return user;
    }

    private void saveAuthority(User user) {
        UserRole userRole = UserRole.builder()
                .user(user)
                .authority(Authority.ROLE_STUDENT)
                .build();

        userRoleRepository.save(userRole);
    }

}
