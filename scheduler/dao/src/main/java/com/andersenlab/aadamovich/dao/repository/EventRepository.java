package com.andersenlab.aadamovich.dao.repository;

import com.andersenlab.aadamovich.dao.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository <EventEntity, Integer> {

    int deleteEventEntityById(Integer id);
}
