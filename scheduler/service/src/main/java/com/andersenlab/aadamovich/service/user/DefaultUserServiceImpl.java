package com.andersenlab.aadamovich.service.user;

import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import com.andersenlab.aadamovich.model.Role;
import com.andersenlab.aadamovich.model.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DefaultUserServiceImpl implements UserService {

    private final UserBaseDao userDao;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserServiceImpl(UserBaseDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO Tests and check login is taken
    @Override
    public UserDto saveUser(UserDto dto) {
        dto.setRole(Role.USER);
        final String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);
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
