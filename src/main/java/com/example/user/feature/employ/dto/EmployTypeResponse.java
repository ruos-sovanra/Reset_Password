package com.example.user.feature.employ.dto;

import lombok.Builder;

@Builder
public record EmployTypeResponse(
        String id,
        String employType
) {
}
