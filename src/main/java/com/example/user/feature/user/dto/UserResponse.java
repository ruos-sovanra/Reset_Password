package com.example.user.feature.user.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        String id,
        String firstName,
        String lastName,
        String username,
        String email,
        String phone,
        String created_at,
        String updated_at,
        Boolean isDisabled,
        Boolean isVerified,
        String avatar,
        String coverUrl,
        String roleName,
        String AccTypeName
) {
}
