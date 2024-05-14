package com.example.user.feature.user.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        String id,
        String first_name,
        String last_name,
        String username,
        String email,
        String phone,
        String created_at,
        String updated_at,
        boolean isDisabled,
        boolean isVerify,
        String avatar,
        String password
) {
}
