package com.multithreading.repositories;

import com.multithreading.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Products, Integer> {
}
