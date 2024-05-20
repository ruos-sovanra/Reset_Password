package com.example.user.features.auth;


import com.example.user.features.auth.dto.AuthRequest;
import com.example.user.features.auth.dto.AuthResponse;
import com.example.user.features.auth.dto.RefreshTokenRequest;

public interface AuthService {
    AuthResponse login(AuthRequest loginRequest);
   AuthResponse refresh(RefreshTokenRequest request);
    void logout(String refreshToken);
}
