package com.multithreading.controller;

import com.multithreading.entities.CommonData;
import com.multithreading.entities.Products;
import com.multithreading.entities.Users;
import com.multithreading.repositories.UserRepository;
import com.multithreading.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/main-api")
public class MainController {

    @Autowired
    private GenericService genericService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value="/saveUsers")
    public void saveUsers() throws IOException {
        genericService.saveUsersToDB();
    }

    @PostMapping(value="/saveProducts")
    public void saveProducts() throws IOException {
        genericService.saveProductsToDB();
    }

    @PostMapping(value="/saveOrders")
    public void saveOrders() throws IOException {
        genericService.saveOrdersToDB();
    }


    @GetMapping("/getCombinedData")
    public List<CommonData> getCombinedData() throws ExecutionException, InterruptedException {
        return genericService.getAlldDataAndThenCombined().get();
    }

    @GetMapping("/getUsers")
    public List<Users> getUserData() throws ExecutionException, InterruptedException {
        return userRepository.findAll();
    }
}

