package com.example.user.feature.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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
