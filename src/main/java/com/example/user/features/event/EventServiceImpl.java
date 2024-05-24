package com.example.user.features.event;

import com.example.user.domain.EventType;
import com.example.user.features.event.dto.EventRequest;
import com.example.user.features.event.dto.EventRespone;
import com.example.user.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;
    @Override
    public List<EventRespone> getAllEvents() {

        var events = eventRepository.findAll();

        return events.stream().map(eventMapper::mapToEventRespone)
                .toList();
    }

    @Override
    public EventRespone createEvent(EventRequest eventRequest) {

        var event = eventMapper.mapToEvents(eventRequest);

        event.setScheduledDate(LocalDateTime.now());

        event.setEventDescription(eventRequest.eventDec());

        event.setEventName(eventRequest.eventName());

        return eventMapper.mapToEventRespone(eventRepository.save(event));
    }

    @Override
    public EventRespone updateEvent(EventRequest eventRequest, String id) {

        var eventId = eventRepository.findEventsById(id).orElseThrow(()->
                new NoSuchElementException("Event not found "));

        var event = eventMapper.mapToEvents(eventRequest);

        event.setId(eventId.getId());
        event.setScheduledDate(LocalDateTime.now());
        event.setEventDescription(eventRequest.eventDec());
        event.setEventName(eventRequest.eventName());

        return eventMapper.mapToEventRespone(eventRepository.save(event));
    }

    @Override
    public EventRespone deleteEvent(String id) {

        var event = eventRepository.findEventsById(id).orElseThrow(()->
                new NoSuchElementException("Event not found "));

        eventRepository.delete(event);

        return eventMapper.mapToEventRespone(event);
    }

    @Override
    public EventRespone getEventById(String id) {

        var event = eventRepository.findEventsById(id).orElseThrow(()->
                new NoSuchElementException("Event not found "));

        return eventMapper.mapToEventRespone(event);
    }

    @Override
    public Page<EventRespone> getByPageNation(int page, int size) {

        if(page < 0) {
            throw new IllegalArgumentException("Page number cannot be less than 0");
        }
        if(size < 1) {
            throw new IllegalArgumentException("Size number cannot be less than 1");
        }

        //make sort request

        Sort sort = Sort.by(Sort.Direction.ASC,"eventDescription");

       PageRequest pageable= PageRequest.of(page,size,sort);

        Page<EventType> events = eventRepository.findAll(pageable);
        return events.map(eventMapper::mapToEventRespone);
    }
}







