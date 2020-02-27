package com.andersenlab.aadamovich.dao.event;

import com.andersenlab.aadamovich.model.dto.EventDto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultEventDaoImpl implements EventBaseDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public EventDto add(EventDto eventDto) {
        return null;
    }

    @Override
    public EventDto getById(Integer id) {
        return null;
    }

    @Override
    public boolean update(EventDto eventDto) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
