package com.example.user.features.calendarapi;
import com.example.user.features.calendarapi.dto.CalendaRequest;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody CalendaRequest calendaRequest) throws Exception {
        Event eventDetails = new Event()
                .setSummary(calendaRequest.summary());
        return ResponseEntity.ok(eventDetails);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents() {
        try {
            List<Event> events = calendarService.getEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            System.err.println("Failed to retrieve events: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Event convertToEvent(EventDTO eventDTO) {
        Event event = new Event();
        if (eventDTO.getStart() != null && eventDTO.getEnd() != null) {
            try {
                DateTime startDateTime = new DateTime(eventDTO.getStart().getDateTime());
                DateTime endDateTime = new DateTime(eventDTO.getEnd().getDateTime());

                EventDateTime startEventDateTime = new EventDateTime()
                        .setDateTime(startDateTime)
                        .setTimeZone(eventDTO.getStart().getTimeZone());
                EventDateTime endEventDateTime = new EventDateTime()
                        .setDateTime(endDateTime)
                        .setTimeZone(eventDTO.getEnd().getTimeZone());

                event.setStart(startEventDateTime);
                event.setEnd(endEventDateTime);
            } catch (Exception e) {
                System.err.println("Error parsing DateTime: " + e.getMessage());

                throw new IllegalArgumentException("Invalid date/time format provided.", e);
            }
        } else {
            throw new IllegalArgumentException("Both start and end date/times must be provided.");
        }
        return event;
    }


    @PostMapping("/events")
    public ResponseEntity<Event> createEvent1(@RequestBody EventDTO eventDTO) throws Exception {
        Event eventDetails = convertToEvent(eventDTO);
        System.out.println("Event details: " + eventDetails);
        calendarService.createEvent(eventDetails);
        return  ResponseEntity.ok(eventDetails);
    }



}
