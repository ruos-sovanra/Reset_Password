package com.example.user.feature.share.dto;

import lombok.Builder;

@Builder
public record LikeShareUpdateRequest(
        Integer likes
) {
}
