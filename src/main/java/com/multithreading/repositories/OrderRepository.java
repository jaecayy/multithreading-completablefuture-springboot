package com.multithreading.repositories;

import com.multithreading.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Query(value = "select * from orders_multi where order_id > 127",nativeQuery = true)
    public List<Orders> getAllUsers();
}
