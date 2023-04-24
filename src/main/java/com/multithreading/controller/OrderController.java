package com.multithreading.controller;

import com.multithreading.entities.Orders;
import com.multithreading.repositories.OrderRepository;
import com.multithreading.service.OrdersService;
import org.apache.coyote.Response;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrdersService ordersService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Orders>> getListOfAllOrders(){
//        return orderRepository.findAll();
        List<Orders> listOfOrders = ordersService.getAllOrders();
        return ResponseEntity.ok(listOfOrders);
    }
}
