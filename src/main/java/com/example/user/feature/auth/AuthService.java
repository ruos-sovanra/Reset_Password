package com.example.user.feature.auth;


import com.example.user.feature.auth.dto.AuthRequest;
import com.example.user.feature.auth.dto.AuthResponse;
import com.example.user.feature.auth.dto.RefreshTokenRequest;

public interface AuthService {
    AuthResponse login(AuthRequest loginRequest);
   AuthResponse refresh(RefreshTokenRequest request);
    void logout(String refreshToken);
}
