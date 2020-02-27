package com.andersenlab.aadamovich.dao.converter;

import com.andersenlab.aadamovich.dao.entity.UserEntity;
import com.andersenlab.aadamovich.model.Role;
import com.andersenlab.aadamovich.model.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserConverterTest {

    private final Integer TEST_ID = 1;
    private final String TEST_LOGIN = "Test Login";
    private final String TEST_PASSWORD = "Test Password";
    private final Role TEST_ROLE = Role.USER;

    @Test
    void toDtoNull() {
        assertNull(UserConvert.toDto(null));
    }

    @Test
    void toDtoNotNull() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(TEST_ID);
        userEntity.setLogin(TEST_LOGIN);
        userEntity.setPassword(TEST_PASSWORD);
        userEntity.setRole(TEST_ROLE);

        final UserDto testUserDto = UserConvert.toDto(userEntity);
        assertNotNull(testUserDto);
        assertEquals(userEntity.getId(), testUserDto.getId());
        assertEquals(userEntity.getLogin(), testUserDto.getLogin());
        assertEquals(userEntity.getPassword(), testUserDto.getPassword());
        assertEquals(userEntity.getRole(), testUserDto.getRole());
    }

    @Test
    void toEntityNull() {
        assertNull(UserConvert.toEntity(null));
    }

    @Test
    void toEntityNotNull() {
        final UserDto userDto = new UserDto();
        userDto.setId(TEST_ID);
        userDto.setLogin(TEST_LOGIN);
        userDto.setPassword(TEST_PASSWORD);
        userDto.setRole(TEST_ROLE);

        final UserEntity testUserEntity = UserConvert.toEntity(userDto);
        assertNotNull(testUserEntity);
        assertEquals(userDto.getId(), testUserEntity.getId());
        assertEquals(userDto.getLogin(), testUserEntity.getLogin());
        assertEquals(userDto.getPassword(), testUserEntity.getPassword());
        assertEquals(userDto.getRole(), testUserEntity.getRole());
    }
}
