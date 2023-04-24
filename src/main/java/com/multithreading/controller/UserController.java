package com.multithreading.controller;

import com.multithreading.entities.Users;
import com.multithreading.repositories.UserRepository;
import com.multithreading.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersService usersService;

    @GetMapping(value="/")
    public List<Users> getListOfAllUsers(){
       return usersService.getAllUsers();
    }
}
