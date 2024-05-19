package com.example.user.mapper;

import com.example.user.domain.EventType;
import com.example.user.feature.event.dto.EventRequest;
import com.example.user.feature.event.dto.EventRespone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "postType", ignore = true)
    EventRespone mapToEventRespone(EventType eventType);
    @Mapping(target = "postType", ignore = true)
    EventType mapToEvents (EventRequest eventRequest);

}
