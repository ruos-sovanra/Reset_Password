package com.example.user.features.cv.dto;

import lombok.Builder;

@Builder
public record CvRespone(
        String downloadUrl,
        String fileType, float size,
        String filename, String fullUrl,
        String content
) {
}
