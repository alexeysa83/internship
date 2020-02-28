package com.andersenlab.aadamovich.dao.converter;

import com.andersenlab.aadamovich.dao.entity.UserEntity;
import com.andersenlab.aadamovich.model.dto.UserDto;

public abstract class UserConvert {

    private UserConvert() {
    }

    public static UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        final UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setLogin(userEntity.getLogin());
        userDto.setPassword(userEntity.getPassword());
        userDto.setRole(userEntity.getRole());
        return userDto;
    }

    public static UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        final UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setLogin(userDto.getLogin());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRole(userDto.getRole());
        return userEntity;
    }
}
