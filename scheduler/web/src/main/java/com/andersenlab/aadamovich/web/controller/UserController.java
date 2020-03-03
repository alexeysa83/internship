package com.andersenlab.aadamovich.web.controller;

import com.andersenlab.aadamovich.model.dto.UserDto;
import com.andersenlab.aadamovich.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/forward_to_registration_form")
    public ModelAndView forwardToRegistrationForm(ModelAndView model) {
        model.setViewName("registration_form");
        return model;
    }

    //TODO BindingResult
    @PostMapping(value = "/add")
    public ModelAndView createNewUser(UserDto dto, ModelAndView model) {
        final UserDto userFromDB = userService.saveUser(dto);
        model.addObject("user", userFromDB);
        model.setViewName("main");
        return model;
    }
}
