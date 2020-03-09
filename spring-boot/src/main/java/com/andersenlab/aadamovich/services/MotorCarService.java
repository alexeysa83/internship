package com.andersenlab.aadamovich.services;

import com.andersenlab.aadamovich.entities.car.MotorCar;

import java.util.List;

public interface MotorCarService {

    List<MotorCar> getAllMotorCars();

    boolean saveNewMotorCar(MotorCar motorCar);

    boolean deleteMotorCar(Long id);
}
