package com.andersenlab.aadamovich.service.event;

import com.andersenlab.aadamovich.dao.event.EventBaseDao;
import com.andersenlab.aadamovich.model.dto.EventDto;

public class DefaultEventServiceImpl implements EventService {

    private final EventBaseDao eventDao;

    public DefaultEventServiceImpl(EventBaseDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public EventDto saveEvent(EventDto dto) {
        return eventDao.save(dto);
    }

    @Override
    public EventDto getEventById(Integer id) {
        return eventDao.findById(id);
    }

    @Override
    public boolean updateEvent(EventDto dto) {
        return eventDao.update(dto);
    }

    @Override
    public boolean deleteEvent(Integer id) {
        return eventDao.delete(id);
    }
}
