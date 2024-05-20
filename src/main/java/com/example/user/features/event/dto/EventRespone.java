package com.example.user.features.event.dto;

import java.time.LocalDateTime;

public record EventRespone(
        String id,
        String eventType,
        LocalDateTime scheduled,
        String eventName,
        String eventPoster,
        String eventDec,
        String postType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
