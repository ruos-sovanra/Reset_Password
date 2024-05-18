package com.example.user.feature.social.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PostRequest(
        String userId,
        String caption,
        List<Thumbnail> thumbnails
) {
}
