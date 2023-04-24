package com.multithreading.controller;

import com.multithreading.entities.Products;
import com.multithreading.repositories.ProductRepository;
import com.multithreading.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping(value="/")
    public ResponseEntity<List<Products>> getListOfProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
