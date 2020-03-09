package com.andersenlab.aadamovich.services;

import com.andersenlab.aadamovich.entities.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    boolean saveNewUser(UserEntity userEntity);

    boolean deleteUser(String username);
}
