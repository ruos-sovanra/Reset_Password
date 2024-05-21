package com.example.user.features.usersocialmedia.dto;

import lombok.Builder;

@Builder
public record SocialMediaUpdateRequest(
        String name,
        String socialMediaLink
){
}
