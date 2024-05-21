package com.example.user.features.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jdk.jfr.Timestamp;
import org.hibernate.annotations.TimeZoneColumn;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public record EventRespone(
        String id,
        String eventType,
        LocalDateTime scheduled,
        String eventName,
        String eventPoster,
        String eventDec,
        String postType,

        Time eventStart,

        Time eventEnd,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
