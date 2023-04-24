package com.multithreading.service;

import com.multithreading.entities.Users;
import com.multithreading.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
}
