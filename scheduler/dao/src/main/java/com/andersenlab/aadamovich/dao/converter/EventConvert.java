package com.andersenlab.aadamovich.dao.converter;

import com.andersenlab.aadamovich.dao.entity.EventEntity;
import com.andersenlab.aadamovich.model.dto.EventDto;

public abstract class EventConvert {

    private EventConvert() {
    }

    public static EventDto toDto(EventEntity eventEntity) {
        if (eventEntity == null) {
            return null;
        }

        final EventDto eventDto = new EventDto();
        eventDto.setId(eventEntity.getId());
        eventDto.setName(eventEntity.getName());
        eventDto.setDescription(eventEntity.getDescription());
        eventDto.setStartDate(eventEntity.getStartDate());
        eventDto.setStartTime(eventEntity.getStartTime());
        eventDto.setEndTime(eventEntity.getEndTime());
        eventDto.setUserId(eventEntity.getUser().getId());
        return eventDto;
    }

    public static EventEntity toEntity(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        final EventEntity eventEntity = new EventEntity();
        eventEntity.setId(eventDto.getId());
        eventEntity.setName(eventDto.getName());
        eventEntity.setDescription(eventDto.getDescription());
        eventEntity.setStartDate(eventDto.getStartDate());
        eventEntity.setStartTime(eventDto.getStartTime());
        eventEntity.setEndTime(eventDto.getEndTime());
        return eventEntity;
    }
}
