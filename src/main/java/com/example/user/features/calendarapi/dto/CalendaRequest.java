package com.example.user.features.calendarapi.dto;

import lombok.Builder;

@Builder
public record CalendaRequest(
        String summary
) {
}
