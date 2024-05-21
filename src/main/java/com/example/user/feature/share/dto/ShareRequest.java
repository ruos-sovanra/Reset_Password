package com.example.user.feature.share.dto;

import lombok.Builder;

@Builder
public record ShareRequest(
        String userId,
        String socialId,
        String caption

) {
}
