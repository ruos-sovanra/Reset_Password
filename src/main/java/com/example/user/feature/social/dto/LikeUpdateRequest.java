package com.example.user.feature.social.dto;

import lombok.Builder;

@Builder
public record LikeUpdateRequest(
        Integer likes
) {
}
