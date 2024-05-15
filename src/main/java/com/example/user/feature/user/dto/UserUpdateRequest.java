package com.example.user.feature.user.dto;

import lombok.Builder;

@Builder
public record UserUpdateRequest(
        String first_name,
        String last_name,
        String username,
        String phone,
        String avatar
) {
}
