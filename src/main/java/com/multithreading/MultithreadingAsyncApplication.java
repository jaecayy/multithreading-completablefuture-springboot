package com.multithreading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MultithreadingAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultithreadingAsyncApplication.class, args);
	}

}
