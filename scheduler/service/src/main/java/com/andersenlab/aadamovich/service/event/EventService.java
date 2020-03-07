package com.andersenlab.aadamovich.service.event;

import com.andersenlab.aadamovich.model.dto.EventDto;

public interface EventService {

    EventDto saveEvent(EventDto dto);

    EventDto getEventById(Integer id);

    boolean updateEvent(EventDto dto);

    boolean deleteEvent(Integer id);
}
