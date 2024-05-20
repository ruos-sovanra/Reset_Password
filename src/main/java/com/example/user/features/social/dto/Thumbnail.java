package com.example.user.features.social.dto;

import lombok.Builder;

@Builder
public record Thumbnail(
        String thumbnailUrl
) {}