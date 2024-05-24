package com.example.user.features.calendarapi;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDateTimeDTO {
    private String dateTime;
    private String timeZone;
}
