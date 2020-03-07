package com.andersenlab.aadamovich.dao.impl;

import com.andersenlab.aadamovich.dao.config.DaoConfig;
import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import com.andersenlab.aadamovich.model.Role;
import com.andersenlab.aadamovich.model.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DefaultUserDaoImplTest {

    @Autowired
    private UserBaseDao userDao;

    @Test
    void saveUser() {
        final UserDto expectedUser = createUserDto("CreateUserTest");

        final UserDto actualUser = userDao.save(expectedUser);
        assertNotNull(actualUser);
        assertNotNull(actualUser.getId());
        assertEquals(expectedUser.getLogin(), actualUser.getLogin());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getRole(), actualUser.getRole());
    }

    @Test
    void findByIdNotExists() {
        final UserDto userFromDB = userDao.findById(0);
        assertNull(userFromDB);
    }

    @Test
    void findByIdExists() {
        final UserDto userDto = createUserDto("FindByIdTestUser");
        final UserDto expectedUser = userDao.save(userDto);

        final UserDto actualUser = userDao.findById(expectedUser.getId());
        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getLogin(), actualUser.getLogin());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getRole(), actualUser.getRole());
    }

    @Test
    void findByLoginNotExists() {
        final UserDto userFromDB = userDao.findByLogin("This user not exists");
        assertNull(userFromDB);
    }

    @Test
    void findByLoginExists() {
        final UserDto userDto = createUserDto("FindByLoginTestUser");
        final UserDto expectedUser = userDao.save(userDto);

        final UserDto actualUser = userDao.findByLogin(expectedUser.getLogin());
        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getLogin(), actualUser.getLogin());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getRole(), actualUser.getRole());
    }

    @Test
    void updateFail() {
        final UserDto userDto = new UserDto();
        userDto.setId(0);
        final boolean isUpdated = userDao.update(userDto);
        assertFalse(isUpdated);
    }

    @Test
    void updateSuccess() {
        final UserDto userDto = createUserDto("UpdateUserTest");
        final UserDto savedUser = userDao.save(userDto);
        final Integer userId = savedUser.getId();

        final UserDto expectedUser = createUserDto("UPDATED");
        expectedUser.setId(userId);
        expectedUser.setPassword("UpdatedPassword");
        expectedUser.setRole(Role.ADMIN);

        final boolean isUpdated = userDao.update(expectedUser);
        assertTrue(isUpdated);
        final UserDto actualUser = userDao.findById(userId);
        assertNotNull(actualUser);
        assertEquals(expectedUser.getLogin(), actualUser.getLogin());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getRole(), actualUser.getRole());
    }

    @Test
    void deleteFail() {
        final boolean isDeleted = userDao.delete(0);
        assertFalse(isDeleted);
    }

    @Test
    void deleteSuccess() {
        final UserDto userDto = createUserDto("DeleteUserTest");
        final UserDto savedUser = userDao.save(userDto);
        final Integer userId = savedUser.getId();

        final UserDto userToDelete = userDao.findById(userId);
        assertNotNull(userToDelete);

        final boolean isDeleted = userDao.delete(userId);
        assertTrue(isDeleted);

        final UserDto userAfterDelete = userDao.findById(userId);
        assertNull(userAfterDelete);
    }

    private UserDto createUserDto(String login) {
        final UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setPassword("Test Password");
        userDto.setRole(Role.USER);
        return userDto;
    }
}
