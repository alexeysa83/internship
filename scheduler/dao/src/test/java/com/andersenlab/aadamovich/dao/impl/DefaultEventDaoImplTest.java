package com.andersenlab.aadamovich.dao.impl;

import com.andersenlab.aadamovich.dao.config.DaoConfig;
import com.andersenlab.aadamovich.dao.event.EventBaseDao;
import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import com.andersenlab.aadamovich.model.Role;
import com.andersenlab.aadamovich.model.dto.EventDto;
import com.andersenlab.aadamovich.model.dto.UserDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DefaultEventDaoImplTest {

    @Autowired
    private EventBaseDao eventDao;

    @Autowired
    private UserBaseDao userDao;

    private UserDto testUserDto;

    @BeforeAll
    void createTestUser() {
        final UserDto testUser = new UserDto();
        testUser.setLogin("EventUserTest");
        testUser.setPassword("Password");
        testUser.setRole(Role.USER);
        testUserDto = userDao.save(testUser);
    }

    /*
    Deletes all the test event records from database
     */
    @AfterAll
    void deleteTestUser() {
        userDao.delete(testUserDto.getId());
    }

    @Test
    void saveEvent() {
        final EventDto eventDto = createEventDto("CreateEventTest");

        final EventDto eventSavedInDB = eventDao.save(eventDto);
        assertNotNull(eventSavedInDB);
        assertNotNull(eventSavedInDB.getId());
        assertEquals(eventDto.getName(), eventSavedInDB.getName());
        assertEquals(eventDto.getDescription(), eventSavedInDB.getDescription());
        assertEquals(eventDto.getStartDate(), eventSavedInDB.getStartDate());
        assertEquals(eventDto.getStartTime(), eventSavedInDB.getStartTime());
        assertEquals(eventDto.getEndTime(), eventSavedInDB.getEndTime());
        assertEquals(eventDto.getUserId(), eventSavedInDB.getUserId());
    }

    @Test
    void findByIdNotExists() {
        final EventDto eventFromDB = eventDao.findById(0);
        assertNull(eventFromDB);
    }

    @Test
    void findByIdExists() {
        final EventDto eventDto = createEventDto("ReadByIdTestEvent");
        final EventDto savedEvent = eventDao.save(eventDto);

        final EventDto eventFoundInDB = eventDao.findById(savedEvent.getId());
        assertNotNull(eventFoundInDB);
        assertEquals(savedEvent.getId(), eventFoundInDB.getId());
        assertEquals(savedEvent.getName(), eventFoundInDB.getName());
        assertEquals(savedEvent.getDescription(), eventFoundInDB.getDescription());
        assertEquals(savedEvent.getStartDate(), eventFoundInDB.getStartDate());
        assertEquals(savedEvent.getStartTime(), eventFoundInDB.getStartTime());
        assertEquals(savedEvent.getEndTime(), eventFoundInDB.getEndTime());
        assertEquals(savedEvent.getUserId(), eventFoundInDB.getUserId());
    }

    @Test
    void updateFail() {
        final EventDto eventDto = new EventDto();
        eventDto.setId(0);
        final boolean isUpdated = eventDao.update(eventDto);
        assertFalse(isUpdated);
    }

    @Test
    void updateSuccess() {
        final EventDto eventDto = createEventDto("UpdateEventTest");
        final EventDto savedEvent = eventDao.save(eventDto);
        final Integer eventId = savedEvent.getId();

        final EventDto eventToUpdate = createEventDto("UPDATED");
        eventToUpdate.setId(eventId);
        eventToUpdate.setDescription("UPDATED");
        eventToUpdate.setStartDate(LocalDate.of(2019, 3, 20));
        eventToUpdate.setStartTime(LocalTime.of(20, 15));
        eventToUpdate.setEndTime(LocalTime.of(21, 25));

        final boolean isUpdated = eventDao.update(eventToUpdate);
        assertTrue(isUpdated);
        final EventDto eventAfterUpdate = eventDao.findById(eventId);
        assertNotNull(eventAfterUpdate);
        assertEquals(eventToUpdate.getName(), eventAfterUpdate.getName());
        assertEquals(eventToUpdate.getDescription(), eventAfterUpdate.getDescription());
        assertEquals(eventToUpdate.getStartDate(), eventAfterUpdate.getStartDate());
        assertEquals(eventToUpdate.getStartTime(), eventAfterUpdate.getStartTime());
        assertEquals(eventToUpdate.getEndTime(), eventAfterUpdate.getEndTime());
    }

    @Test
    void deleteFail() {
        final boolean isDeleted = eventDao.delete(0);
        assertFalse(isDeleted);
    }

    @Test
    void deleteSuccess() {
        final EventDto eventDto = createEventDto("DeleteEventTest");
        final EventDto savedEvent = eventDao.save(eventDto);
        final Integer eventId = savedEvent.getId();

        final EventDto eventToDelete = eventDao.findById(eventId);
        assertNotNull(eventToDelete);

        final boolean isDeleted = eventDao.delete(eventId);
        assertTrue(isDeleted);

        final EventDto eventAfterDelete = eventDao.findById(eventId);
        assertNull(eventAfterDelete);
    }

    private EventDto createEventDto(String name) {
        final EventDto eventDto = new EventDto();
        eventDto.setName(name);
        eventDto.setDescription("TestDescription");
        eventDto.setStartDate(LocalDate.of(2020, 2, 27));
        eventDto.setStartTime(LocalTime.of(16, 0));
        eventDto.setEndTime(LocalTime.of(17, 0));
        eventDto.setUserId(testUserDto.getId());
        return eventDto;
    }
}
