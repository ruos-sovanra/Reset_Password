package com.example.user.features.auth.dto;

import lombok.Builder;

@Builder
public record RefreshTokenRequest(String refreshToken) {
}
