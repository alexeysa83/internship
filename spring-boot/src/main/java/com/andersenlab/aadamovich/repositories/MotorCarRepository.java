package com.andersenlab.aadamovich.repositories;

import com.andersenlab.aadamovich.entities.car.MotorCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorCarRepository extends JpaRepository <MotorCar, Long> {
}
