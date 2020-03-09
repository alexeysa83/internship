package com.andersenlab.aadamovich.services;

import com.andersenlab.aadamovich.entities.car.MotorCar;
import com.andersenlab.aadamovich.repositories.MotorCarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MotorCarServiceDefaultImpl implements MotorCarService {

    private final MotorCarRepository motorCarRepository;

    public MotorCarServiceDefaultImpl(MotorCarRepository motorCarRepository) {
        this.motorCarRepository = motorCarRepository;
    }

    @Override
    @Transactional
    public List<MotorCar> getAllMotorCars() {
        return motorCarRepository.findAll();
    }

    //TODO implement validation and boolean logic
    @Override
    @Transactional
    public boolean saveNewMotorCar(MotorCar motorCar) {
        motorCarRepository.save(motorCar);
        return true;
    }

    //TODO implement validation and boolean logic
    @Override
    @Transactional
    public boolean deleteMotorCar(Long id) {
        motorCarRepository.deleteById(id);
        return true;
    }
}
