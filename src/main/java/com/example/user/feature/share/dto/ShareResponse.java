package com.example.user.feature.share.dto;

import com.example.user.features.social.dto.PostResponse;
import lombok.Builder;

@Builder
public record ShareResponse(
        String id,
        PostResponse post,
        Integer likes,
        Integer shares
) {
}
