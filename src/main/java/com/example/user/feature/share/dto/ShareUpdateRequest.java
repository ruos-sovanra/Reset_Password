package com.example.user.feature.share.dto;

import lombok.Builder;

@Builder
public record ShareUpdateRequest(
        String caption
) {
}
