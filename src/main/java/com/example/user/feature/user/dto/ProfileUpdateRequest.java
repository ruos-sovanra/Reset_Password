package com.example.user.feature.user.dto;

import lombok.Builder;

@Builder
public record ProfileUpdateRequest(
        String roleName
) {
}
