package com.example.user.features.user.dto;

import lombok.Builder;

@Builder
public record ProfileUpdateRequest(
        String roleName
) {
}
