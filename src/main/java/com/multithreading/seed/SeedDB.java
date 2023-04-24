package com.multithreading.seed;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multithreading.entities.Orders;
import com.multithreading.entities.Products;
import com.multithreading.entities.Users;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class SeedDB<T> {

    public List<Users> fetchDataUsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File("Users.json"),new TypeReference<List<Users>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Products> fetchDataProducts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File("Products.json"),new TypeReference<List<Products>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Orders> fetchDataOrders() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File("Orders.json"),new TypeReference<List<Orders>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
