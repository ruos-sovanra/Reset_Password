package com.example.user.feature.event;

import com.example.user.feature.event.dto.EventRequest;
import com.example.user.feature.event.dto.EventRespone;
import com.example.user.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public List<EventRespone> getByPageNation(int page, int size) {
        int pageSize = page+1;
        Pageable pageable = PageRequest.of(pageSize, size);
        var events = eventRepository.findAll(pageable).getContent();
        return events.stream().map(eventMapper::mapToEventRespone)
                .toList();
    }
}
