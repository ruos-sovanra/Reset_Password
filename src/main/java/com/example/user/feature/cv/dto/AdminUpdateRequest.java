package com.example.user.feature.cv.dto;

public record AdminUpdateRequest(
        String generationId,
        String countryName,
        String employTypeName,
        Boolean isGraduated,
        Boolean isEmployed
) {
}
