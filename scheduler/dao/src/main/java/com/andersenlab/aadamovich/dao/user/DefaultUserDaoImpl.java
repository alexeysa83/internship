package com.andersenlab.aadamovich.dao.user;

import com.andersenlab.aadamovich.dao.converter.UserConvert;
import com.andersenlab.aadamovich.dao.entity.UserEntity;
import com.andersenlab.aadamovich.model.dto.UserDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DefaultUserDaoImpl implements UserBaseDao {

    private final SessionFactory factory;

    public DefaultUserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public UserDto save(UserDto userDto) {
        try (final Session session = factory.openSession()) {
            final UserEntity userToSave = UserConvert.toEntity(userDto);
            final Transaction transaction = session.beginTransaction();
            final Integer userId = (Integer) session.save(userToSave);
            transaction.commit();
            userDto.setId(userId);
            return userDto;
        }
    }

    @Override
    public UserDto findById(Integer id) {
        try (final Session session = factory.openSession()) {
            final UserEntity userEntity = session.get(UserEntity.class, id);
            return UserConvert.toDto(userEntity);
        }
    }

    @Override
    public UserDto findByLogin(String login) {
        try (final Session session = factory.openSession()) {
            final UserEntity userFromDB = (UserEntity) session
                    .createQuery("from UserEntity where login= :login")
                    .setParameter("login", login)
                    .uniqueResult();
            return UserConvert.toDto(userFromDB);
        }
    }

    //TODO boolean logic with HQL and update for particular fields
    @Override
    public boolean update(UserDto userDto) {
        try (final Session session = factory.openSession()) {
            if (userDto.getId() <= 0) {
                return false;
            }
            final UserEntity userToUpdate = UserConvert.toEntity(userDto);
            final Transaction transaction = session.beginTransaction();
            session.update(userToUpdate);
            transaction.commit();
            return true;
        }
    }

    //TODO boolean logic with HQL and in one query
    @Override
    public boolean delete(Integer id) {
        try (final Session session = factory.openSession()) {
            if (id <= 0) {
                return false;
            }
            final Transaction transaction = session.beginTransaction();
            final UserEntity userToDelete = session.load(UserEntity.class, id);
            session.delete(userToDelete);
            transaction.commit();
            return true;
        }
    }
}
