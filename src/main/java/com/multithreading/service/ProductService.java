package com.multithreading.service;

import com.multithreading.entities.Products;
import com.multithreading.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }
}
