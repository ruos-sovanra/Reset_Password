package com.example.user.feature.event.dto;

import java.time.LocalDateTime;

public record EventRespone(
        String id,
        String eventType,
        LocalDateTime schedule,
        String eventName,
        String eventPoster,
        String eventDec,
        String postType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
