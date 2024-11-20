package com.gcms.v3.domain.auth.service.impl;

import com.gcms.v3.domain.auth.domain.repository.RefreshTokenRepository;
import com.gcms.v3.domain.auth.service.CancelMembershipService;
import com.gcms.v3.domain.user.domain.entity.User;
import com.gcms.v3.domain.user.domain.repository.UserRepository;
import com.gcms.v3.domain.user.domain.repository.UserRoleRepository;
import com.gcms.v3.domain.user.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CancelMembershipServiceImpl implements CancelMembershipService {

    private final UserUtil userUtil;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public void execute() {
        User user = userUtil.getCurrentUser();

        refreshTokenRepository.deleteById(user.getEmail());

        userRoleRepository.deleteByUser(user);
        userRepository.deleteById(user.getId());
    }
}
