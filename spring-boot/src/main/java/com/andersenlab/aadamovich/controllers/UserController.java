package com.andersenlab.aadamovich.controllers;

import com.andersenlab.aadamovich.entities.Role;
import com.andersenlab.aadamovich.entities.UserEntity;
import com.andersenlab.aadamovich.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllUsers(ModelAndView model) {
        final List<UserEntity> listOfUsers = userService.getAllUsers();
        model.addObject("users", listOfUsers);
        model.setViewName("admin_page");
        return model;
    }

    //TODO Request model for validation
    @PostMapping
    public ModelAndView addNewUser(UserEntity userEntity, @RequestParam String role, ModelAndView model) {
        String message;
        userEntity.getRoles().add(Role.valueOf(role));
        final boolean wasSaved = userService.saveNewUser(userEntity);
        if(wasSaved) {
            message = String.format("Congratulations, %s, you are successfully registered!", userEntity.getUsername());
        } else {
            message = String.format("Username %s is already taken!", userEntity.getUsername());
        }
        model.addObject("message", message);
        model.setViewName("main");
        return model;
    }

    @PostMapping("/{username}")
    public ModelAndView deleteUser(@PathVariable String username, ModelAndView model) {
        String message;
        final boolean wasDeleted = userService.deleteUser(username);
        if(wasDeleted) {
            message = String.format("User %s is disabled!", username);
        } else {
            message = "Unable to delete user!";
        }
        model.addObject("message", message);
        model.setViewName("main");
        return model;
    }
}
