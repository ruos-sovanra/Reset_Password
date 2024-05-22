package com.example.user.feature.abroad_study.dto;

import lombok.Builder;

@Builder
public record StudyAbroadResponse(
        String id,
        String country
) {
}
