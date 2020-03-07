package com.andersenlab.aadamovich.service.user;

import com.andersenlab.aadamovich.model.dto.UserDto;

public interface UserService {

    UserDto saveUser(UserDto dto);

    UserDto getUserById(Integer id);

    boolean updateUser(UserDto dto);

    boolean deleteUser(Integer id);
}
