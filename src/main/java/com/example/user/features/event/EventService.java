package com.example.user.features.event;

import com.example.user.features.event.dto.EventRespone;
import com.example.user.features.event.dto.EventRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EventService {

    List<EventRespone> getAllEvents();

    EventRespone createEvent(EventRequest eventRequest);

    EventRespone updateEvent(EventRequest eventRequest,String id);

    EventRespone deleteEvent (String id);

    EventRespone getEventById(String id);

    Page<EventRespone> getByPageNation(int page, int size);
}
