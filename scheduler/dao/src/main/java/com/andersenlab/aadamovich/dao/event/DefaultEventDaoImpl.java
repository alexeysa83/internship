package com.andersenlab.aadamovich.dao.event;

import com.andersenlab.aadamovich.dao.converter.EventConvert;
import com.andersenlab.aadamovich.dao.entity.EventEntity;
import com.andersenlab.aadamovich.dao.entity.UserEntity;
import com.andersenlab.aadamovich.model.dto.EventDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DefaultEventDaoImpl implements EventBaseDao {

    private final SessionFactory factory;

    public DefaultEventDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public EventDto save(EventDto eventDto) {
        try (final Session session = factory.openSession()) {
            final EventEntity eventToSave = EventConvert.toEntity(eventDto);
            final Transaction transaction = session.beginTransaction();
            final UserEntity userProxy = session.load(UserEntity.class, eventDto.getUserId());
            eventToSave.setUser(userProxy);
            final Integer eventId = (Integer) session.save(eventToSave);
            transaction.commit();
            eventDto.setId(eventId);
            return eventDto;
        }
    }

    @Override
    public EventDto findById(Integer id) {
        try (final Session session = factory.openSession()) {
            final EventEntity eventEntity = session.get(EventEntity.class, id);
            return EventConvert.toDto(eventEntity);
        }
    }

    //TODO boolean logic with HQL and update for particular fields
    @Override
    public boolean update(EventDto eventDto) {
        try (final Session session = factory.openSession()) {
            if (eventDto.getId() <= 0) {
                return false;
            }
            final EventEntity eventToUpdate = EventConvert.toEntity(eventDto);
            final Transaction transaction = session.beginTransaction();
            final UserEntity userProxy = session.load(UserEntity.class, eventDto.getUserId());
            eventToUpdate.setUser(userProxy);
            session.update(eventToUpdate);
            transaction.commit();
            return true;
        }
    }

    //TODO boolean logic with HQL
    @Override
    public boolean delete(Integer id) {
        try (final Session session = factory.openSession()) {
            if (id <= 0) {
                return false;
            }
            final Transaction transaction = session.beginTransaction();
            final EventEntity eventToDelete = session.load(EventEntity.class, id);
            session.delete(eventToDelete);
            transaction.commit();
            return true;
        }
    }
}
