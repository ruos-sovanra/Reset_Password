package com.example.user.features.auth.dto;

import lombok.Builder;

@Builder
public record AuthRequest(
        String email,
        String password
) {
}
