package com.example.user.feature.abroad_study.dto;

import lombok.Builder;

@Builder
public record StudyAbroadRequest(
        String country
) {
}
