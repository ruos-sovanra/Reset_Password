package com.example.user.features.usersocialmedia.dto;

import com.example.user.features.user.dto.UserResponse;

public record SocialMediaRespoen(
        String id,
        String name,
        String socialMediaLink,
        UserResponse userResponse
) {
}
