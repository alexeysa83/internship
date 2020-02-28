package com.andersenlab.aadamovich.dao.converter;

import com.andersenlab.aadamovich.dao.entity.EventEntity;
import com.andersenlab.aadamovich.dao.entity.UserEntity;
import com.andersenlab.aadamovich.model.dto.EventDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventConverterTest {

    private final Integer TEST_ID = 1;
    private final Integer TEST_USER_ID = 1;
    private final String TEST_NAME = "Test Event";
    private final String TEST_DESCRIPTION = "Test Description";
    private final LocalDate TEST_START_DATE = LocalDate.of(2020, 2, 27);
    private final LocalTime TEST_START_TIME = LocalTime.of(16, 0);
    private final LocalTime TEST_END_TIME = LocalTime.of(17, 0);

    @Test
    void toDtoNull() {
        assertNull(EventConvert.toDto(null));
    }

    @Test
    void toDtoNotNull() {
        final EventEntity eventEntity = new EventEntity();
        eventEntity.setId(TEST_ID);
        eventEntity.setName(TEST_NAME);
        eventEntity.setDescription(TEST_DESCRIPTION);
        eventEntity.setStartDate(TEST_START_DATE);
        eventEntity.setStartTime(TEST_START_TIME);
        eventEntity.setEndTime(TEST_END_TIME);
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(TEST_USER_ID);
        eventEntity.setUser(userEntity);

        final EventDto testEventDto = EventConvert.toDto(eventEntity);
        assertNotNull(testEventDto);
        assertEquals(eventEntity.getId(), testEventDto.getId());
        assertEquals(eventEntity.getName(), testEventDto.getName());
        assertEquals(eventEntity.getDescription(), testEventDto.getDescription());
        assertEquals(eventEntity.getStartDate(), testEventDto.getStartDate());
        assertEquals(eventEntity.getStartTime(), testEventDto.getStartTime());
        assertEquals(eventEntity.getEndTime(), testEventDto.getEndTime());
        assertEquals(eventEntity.getUser().getId(), testEventDto.getUserId());
    }

    @Test
    void toEntityNull() {
        assertNull(EventConvert.toEntity(null));
    }

    @Test
    void toEntityNotNull() {
        final EventDto eventDto = new EventDto();
        eventDto.setId(TEST_ID);
        eventDto.setName(TEST_NAME);
        eventDto.setDescription(TEST_DESCRIPTION);
        eventDto.setStartDate(TEST_START_DATE);
        eventDto.setStartTime(TEST_START_TIME);
        eventDto.setEndTime(TEST_END_TIME);

        final EventEntity testEventEntity = EventConvert.toEntity(eventDto);
        assertNotNull(testEventEntity);
        assertEquals(eventDto.getId(), testEventEntity.getId());
        assertEquals(eventDto.getName(), testEventEntity.getName());
        assertEquals(eventDto.getDescription(), testEventEntity.getDescription());
        assertEquals(eventDto.getStartDate(), testEventEntity.getStartDate());
        assertEquals(eventDto.getStartTime(), testEventEntity.getStartTime());
        assertEquals(eventDto.getEndTime(), testEventEntity.getEndTime());
    }
}
