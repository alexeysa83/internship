package com.andersenlab.aadamovich.service.user;

import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import com.andersenlab.aadamovich.model.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class DefaultUserServiceImpl implements UserService {

    private final UserBaseDao userDao;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserServiceImpl(UserBaseDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO Tests and check login is taken
    @Override
    @Transactional
    public UserDto saveUser(UserDto dto) {
        final String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);
        return userDao.save(dto);
    }

    @Override
    @Transactional
    public UserDto getUserById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public boolean updateUser(UserDto dto) {
        return userDao.update(dto);
    }

    @Override
    @Transactional
    public boolean deleteUser(Integer id) {
        return userDao.delete(id);
    }
}
