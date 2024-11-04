package com.gcms.v3.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;

public interface LogoutService {
    void execute(HttpServletRequest request);
}
