package com.andersenlab.aadamovich.services;

import com.andersenlab.aadamovich.entities.user.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    boolean saveNewUser(UserEntity userEntity);

    boolean deleteUser(String username);
}
