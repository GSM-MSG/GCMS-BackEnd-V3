package com.gcms.v3.domain.user.domain.repository;

import com.gcms.v3.domain.user.domain.entity.User;
import com.gcms.v3.domain.user.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, byte[]> {
    List<UserRole> findByUser(User user);
}
