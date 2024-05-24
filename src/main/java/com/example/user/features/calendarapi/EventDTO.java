package com.example.user.features.calendarapi;

import lombok.Data;

@Data
public class EventDTO {
    private String summary;
    private String description;
    private String visibility;
    private EventDateTimeDTO end;
    private EventDateTimeDTO start;

}
