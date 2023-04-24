package com.multithreading.service;

import com.multithreading.entities.Orders;
import com.multithreading.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }
}
