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
        final EventDto expectedEvent = createEventDto("CreateEventTest");

        final EventDto actualEvent = eventDao.save(expectedEvent);
        assertNotNull(actualEvent);
        assertNotNull(actualEvent.getId());
        assertEquals(expectedEvent.getName(), actualEvent.getName());
        assertEquals(expectedEvent.getDescription(), actualEvent.getDescription());
        assertEquals(expectedEvent.getStartDate(), actualEvent.getStartDate());
        assertEquals(expectedEvent.getStartTime(), actualEvent.getStartTime());
        assertEquals(expectedEvent.getEndTime(), actualEvent.getEndTime());
        assertEquals(expectedEvent.getUserId(), actualEvent.getUserId());
    }

    @Test
    void findByIdNotExists() {
        final EventDto eventFromDB = eventDao.findById(0);
        assertNull(eventFromDB);
    }

    @Test
    void findByIdExists() {
        final EventDto eventDto = createEventDto("ReadByIdTestEvent");
        final EventDto expectedEvent = eventDao.save(eventDto);

        final EventDto actualEvent = eventDao.findById(expectedEvent.getId());
        assertNotNull(actualEvent);
        assertEquals(expectedEvent.getId(), actualEvent.getId());
        assertEquals(expectedEvent.getName(), actualEvent.getName());
        assertEquals(expectedEvent.getDescription(), actualEvent.getDescription());
        assertEquals(expectedEvent.getStartDate(), actualEvent.getStartDate());
        assertEquals(expectedEvent.getStartTime(), actualEvent.getStartTime());
        assertEquals(expectedEvent.getEndTime(), actualEvent.getEndTime());
        assertEquals(expectedEvent.getUserId(), actualEvent.getUserId());
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

        final EventDto expectedEvent = createEventDto("UPDATED");
        expectedEvent.setId(eventId);
        expectedEvent.setDescription("UPDATED");
        expectedEvent.setStartDate(LocalDate.of(2019, 3, 20));
        expectedEvent.setStartTime(LocalTime.of(20, 15));
        expectedEvent.setEndTime(LocalTime.of(21, 25));

        final boolean isUpdated = eventDao.update(expectedEvent);
        assertTrue(isUpdated);
        final EventDto actualEvent = eventDao.findById(eventId);
        assertNotNull(actualEvent);
        assertEquals(expectedEvent.getName(), actualEvent.getName());
        assertEquals(expectedEvent.getDescription(), actualEvent.getDescription());
        assertEquals(expectedEvent.getStartDate(), actualEvent.getStartDate());
        assertEquals(expectedEvent.getStartTime(), actualEvent.getStartTime());
        assertEquals(expectedEvent.getEndTime(), actualEvent.getEndTime());
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
