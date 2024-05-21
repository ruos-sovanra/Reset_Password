package com.example.user.feature.generation.dto;

import lombok.Builder;

@Builder
public record GenerationRequest(
        String nameType,
        Integer Generation
) {
}
