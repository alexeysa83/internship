package com.andersenlab.aadamovich.controllers;

import com.andersenlab.aadamovich.entities.car.MotorCar;
import com.andersenlab.aadamovich.services.MotorCarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/motor_cars")
public class MotorCarController {

    private final MotorCarService motorCarService;

    public MotorCarController(MotorCarService motorCarService) {
        this.motorCarService = motorCarService;
    }

    @GetMapping
    public ModelAndView getAllMotorCars(ModelAndView model) {
        final List<MotorCar> cars = motorCarService.getAllMotorCars();
        model.addObject("cars", cars);
        model.setViewName("motor_car_page");
        return model;
    }

    //TODO boolean logic
    @PostMapping
    public ModelAndView addNewMotorCar(MotorCar motorCar, ModelAndView model) {
        String message;
        final boolean wasSaved = motorCarService.saveNewMotorCar(motorCar);
        if (wasSaved) {
            message = "New motor car was added!";
        } else {
            message = "Unable to add new motor car!";
        }
        final List<MotorCar> cars = motorCarService.getAllMotorCars();
        model.addObject("message", message);
        model.addObject("cars", cars);
        model.setViewName("motor_car_page");
        return model;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteMotorCarById(@PathVariable Long id, ModelAndView model) {
        String message;
        final boolean wasDeleted = motorCarService.deleteMotorCar(id);
                if (wasDeleted) {
            message = "Motor car was deleted!";
        } else {
            message = "Unable to delete motor car!";
        }
        final List<MotorCar> cars = motorCarService.getAllMotorCars();
        model.addObject("message", message);
        model.addObject("cars", cars);
        model.setViewName("motor_car_page");
        return model;
    }

}
