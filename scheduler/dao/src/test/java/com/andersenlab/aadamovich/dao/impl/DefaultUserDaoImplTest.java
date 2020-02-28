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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultUserDaoImplTest {

    @Autowired
    private UserBaseDao userDao;

    @Test
    void saveUser() {
        final UserDto userDto = createUserDto("CreateUserTest");

        final UserDto userSavedInDB = userDao.save(userDto);
        assertNotNull(userSavedInDB);
        assertNotNull(userSavedInDB.getId());
        assertEquals(userDto.getLogin(), userSavedInDB.getLogin());
        assertEquals(userDto.getPassword(), userSavedInDB.getPassword());
        assertEquals(userDto.getRole(), userSavedInDB.getRole());

        userDao.delete(userSavedInDB.getId());
    }

    @Test
    void findByIdNotExists() {
        final UserDto userFromDB = userDao.findById(0);
        assertNull(userFromDB);
    }

    @Test
    void findByIdExists() {
        final UserDto userDto = createUserDto("ReadByIdTestUser");
        final UserDto savedUser = userDao.save(userDto);

        final UserDto userFoundInDB = userDao.findById(savedUser.getId());
        assertNotNull(userFoundInDB);
        assertEquals(savedUser.getId(), userFoundInDB.getId());
        assertEquals(savedUser.getLogin(), userFoundInDB.getLogin());
        assertEquals(savedUser.getPassword(), userFoundInDB.getPassword());
        assertEquals(savedUser.getRole(), userFoundInDB.getRole());

        deleteTestUserFromDB(userFoundInDB.getId());
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

        final UserDto userToUpdate = createUserDto("UPDATED");
        userToUpdate.setId(userId);
        userToUpdate.setPassword("UpdatedPassword");
        userToUpdate.setRole(Role.ADMIN);

        final boolean isUpdated = userDao.update(userToUpdate);
        assertTrue(isUpdated);
        final UserDto userAfterUpdate = userDao.findById(userId);
        assertNotNull(userAfterUpdate);
        assertEquals(userToUpdate.getLogin(), userAfterUpdate.getLogin());
        assertEquals(userToUpdate.getPassword(), userAfterUpdate.getPassword());
        assertEquals(userToUpdate.getRole(), userAfterUpdate.getRole());

        deleteTestUserFromDB(userId);
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

    private void deleteTestUserFromDB(Integer id) {
        userDao.delete(id);
    }
}
