package com.example.user.features.usersocialmedia.dto;

import lombok.Builder;

@Builder
public record SocialMediaRequest(
        String userId,
        String name,
        String socialMediaLink
) {
}
