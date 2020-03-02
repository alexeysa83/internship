package com.andersenlab.aadamovich.service.user;

import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import com.andersenlab.aadamovich.model.dto.UserDto;

public class DefaultUserServiceImpl implements UserService{

    private final UserBaseDao userDao;

    public DefaultUserServiceImpl(UserBaseDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto saveUser(UserDto dto) {
        return userDao.save(dto);
    }

    @Override
    public UserDto getUserById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public boolean updateUser(UserDto dto) {
        return userDao.update(dto);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return userDao.delete(id);
    }
}
