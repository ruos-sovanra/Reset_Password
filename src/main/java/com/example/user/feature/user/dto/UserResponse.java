package com.example.user.feature.user.dto;

import lombok.Builder;

import java.util.List;

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
        boolean is_disabled,
        boolean is_verified,
        String avatar,
        String password,
        String roleName,
        String AccTypeName
) {
}
