package com.example.user.features.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        String id,
        String firstName,
        String lastName,
        String username,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean isDisabled,
        Boolean isVerified,
        String avatar,
        String roleName,
        String AccTypeName
) {
}
