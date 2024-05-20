package com.example.user.feature.event;

import com.example.user.feature.event.dto.EventRequest;
import com.example.user.feature.event.dto.EventRespone;
import com.example.user.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public BaseResponse<List<EventRespone>> getAllEvents()
    {
        return BaseResponse.<List<EventRespone>>ok()
                .setPayload(eventService.getAllEvents());
    }

    @PostMapping
    public BaseResponse<EventRespone> createEvent(@RequestBody EventRequest eventRequest)
    {
        return BaseResponse.<EventRespone>ok()
                .setPayload(eventService.createEvent(eventRequest));
    }

    @PutMapping("/{id}")
    public BaseResponse<EventRespone> updateEvent(@RequestBody EventRequest eventRequest, @PathVariable String id)
    {
        return BaseResponse.<EventRespone>ok()
                .setPayload(eventService.updateEvent(eventRequest, id));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<EventRespone> deleteEvent(@PathVariable String id)
    {
        return BaseResponse.<EventRespone>deleteSuccess()
                .setPayload(eventService.deleteEvent(id));
    }

    @GetMapping("/{id}")
    public BaseResponse<EventRespone> getEventById(@PathVariable String id)
    {
        return BaseResponse.<EventRespone>ok()
                .setPayload(eventService.getEventById(id));
    }

    @GetMapping("/page")
    public BaseResponse<Page<EventRespone>> getByPageNation(@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "1") int size)
    {
        return BaseResponse.<Page<EventRespone>>ok()
                .setPayload(eventService.getByPageNation(page, size));
    }
}
