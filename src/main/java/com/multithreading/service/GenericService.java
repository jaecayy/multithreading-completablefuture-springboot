package com.multithreading.service;

import com.multithreading.entities.CommonData;
import com.multithreading.entities.Orders;
import com.multithreading.entities.Products;
import com.multithreading.entities.Users;
import com.multithreading.repositories.OrderRepository;
import com.multithreading.repositories.ProductRepository;
import com.multithreading.repositories.UserRepository;
import com.multithreading.seed.SeedDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class GenericService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    SeedDB seedDB;

    public void saveUsersToDB() throws IOException {

        List<Users> usersList = seedDB.fetchDataUsers();
        //to convert it into type Users you can use ObjectMapper by default generic is returning as LinkedHashMap
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.readValue(usersList, new TypeReference<List<Users>>() {});
        userRepository.saveAll(usersList);
    }

    public void saveProductsToDB() throws IOException {

        List<Products> productsList = seedDB.fetchDataProducts();
        productRepository.saveAll(productsList);
    }

    public void saveOrdersToDB() throws IOException {

        List<Orders> ordersList = seedDB.fetchDataOrders();
        orderRepository.saveAll(ordersList);
    }


    public CompletableFuture<List<CommonData>> getAlldDataAndThenCombined() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<List<Orders>> ordersList = CompletableFuture.supplyAsync(()->{
            System.out.println("Executed thread name : => "+Thread.currentThread().getName());
            return orderRepository.findAll();
        },executor);

        CompletableFuture<List<Products>> productsList = CompletableFuture.supplyAsync(()->{
            System.out.println("Executed thread name : => "+Thread.currentThread().getName());
            return productRepository.findAll();
        },executor);

        CompletableFuture<List<Users>> usersList = CompletableFuture.supplyAsync(()->{
            System.out.println("Executed thread name : => "+Thread.currentThread().getName());
            return userRepository.findAll();
        },executor);

        return CompletableFuture.allOf(ordersList,productsList,usersList)
                .thenApply(allData -> {
                    try {
                        return combineResults(ordersList.get(), productsList.get(), usersList.get());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private List<CommonData> combineResults(List<Orders> resultsA, List<Products> resultsB, List<Users> resultsC) {
        return resultsA.stream()
                .flatMap(a -> resultsB.stream()
                        .filter(b -> b.getProductOrderId().intValue() == a.getOrderId().intValue())
                        .flatMap(b -> resultsC.stream()
                                .filter(c -> c.getUserOrderId().intValue() == a.getOrderId().intValue() && c.getUserOrderId().intValue() == b.getProductOrderId().intValue() )
                                .map(c -> new CommonData(a.getOrderId(),a.getOrderName(),a.getOrderItems(),
                                        b.getProductId(),b.getProductQuantity(),b.getProductCategory(),
                                        c.getUserId(),c.getUserName(),c.getUserAddress()))
                        )
                )
                .collect(Collectors.toList());
    }
}
