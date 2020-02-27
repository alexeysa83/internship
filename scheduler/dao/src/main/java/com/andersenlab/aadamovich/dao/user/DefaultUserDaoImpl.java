package com.andersenlab.aadamovich.dao.user;

import com.andersenlab.aadamovich.model.dto.UserDto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultUserDaoImpl implements UserBaseDao{

    @Autowired
    private SessionFactory factory;

    @Override
    public UserDto add(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getById(Integer id) {
        return null;
    }

    @Override
    public boolean update(UserDto userDto) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
