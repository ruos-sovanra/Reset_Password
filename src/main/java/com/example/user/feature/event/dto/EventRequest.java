package com.example.user.feature.event.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventRequest(
        String eventType,
        LocalDateTime schedule,
        @NotNull(message = "Event name is required")
        @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
        String eventName,
        String eventPoster,
        @NotNull(message = "Event descriptions  is required")
        @Size(min = 10, max = 30, message = "Name must be between 2 and 30 characters")
        String eventDec,
        String postType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
