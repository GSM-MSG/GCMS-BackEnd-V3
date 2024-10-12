package com.gcms.v3.global.security.auth;

import com.gcms.v3.domain.auth.exception.UserNotFoundException;
import com.gcms.v3.domain.user.domain.repository.UserRepository;
import com.gcms.v3.domain.user.domain.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> new AuthDetails(user, userRoleRepository))
                .orElseThrow(UserNotFoundException::new);
    }
}
