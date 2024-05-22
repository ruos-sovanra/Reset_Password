package com.example.user.feature.share.dto;

import com.example.user.domain.Social;
import com.example.user.feature.social.dto.PostResponse;
import lombok.Builder;

@Builder
public record ShareResponse(
        String id,
        String caption,
        PostResponse post,
        Integer likes,
        Integer shares
) {
}
