package com.example.user.features.user.dto;

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
