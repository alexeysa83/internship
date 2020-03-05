package com.andersenlab.aadamovich.dao.event;

import com.andersenlab.aadamovich.dao.aspect.spring_aop.LoggingClassMethods;
import com.andersenlab.aadamovich.dao.converter.EventConvert;
import com.andersenlab.aadamovich.dao.entity.EventEntity;
import com.andersenlab.aadamovich.dao.entity.UserEntity;
import com.andersenlab.aadamovich.dao.repository.EventRepository;
import com.andersenlab.aadamovich.model.dto.EventDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@LoggingClassMethods
public class DefaultEventDaoImpl implements EventBaseDao {

    private final EventRepository eventRepository;

    public DefaultEventDaoImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public EventDto save(EventDto eventDto) {
        final EventEntity eventToSave = EventConvert.toEntity(eventDto);
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(eventDto.getUserId());
        eventToSave.setUser(userEntity);
        final EventEntity savedEvent = eventRepository.save(eventToSave);
        return EventConvert.toDto(savedEvent);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public EventDto findById(Integer id) {
        final Optional<EventEntity> eventFoundInDB = eventRepository.findById(id);
        final EventEntity eventEntityToReturn = eventFoundInDB.orElse(null);
        return EventConvert.toDto(eventEntityToReturn);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean update(EventDto eventDto) {
        boolean isUpdated = false;
        final Optional<EventEntity> eventBeforeUpdate = eventRepository.findById(eventDto.getId());
        final EventEntity eventEntityToUpdate = eventBeforeUpdate.orElse(null);
        if (eventEntityToUpdate != null) {
            eventEntityToUpdate.setName(eventDto.getName());
            eventEntityToUpdate.setDescription(eventDto.getDescription());
            eventEntityToUpdate.setStartDate(eventDto.getStartDate());
            eventEntityToUpdate.setStartTime(eventDto.getStartTime());
            eventEntityToUpdate.setEndTime(eventDto.getEndTime());
            eventRepository.save(eventEntityToUpdate);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean delete(Integer id) {
        final int rowsDeleted = eventRepository.deleteEventEntityById(id);
        return rowsDeleted > 0;
    }
}
