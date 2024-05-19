package com.example.user.feature.user.dto;

import lombok.Builder;

@Builder
public record UserUpdateRequest(
        String firstName,
        String lastName,
        String username,
        String phone,
        String avatar
) {
}
