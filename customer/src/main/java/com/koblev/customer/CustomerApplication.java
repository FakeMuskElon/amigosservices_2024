package com.koblev.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.koblev.clients"
)
public class CustomerApplication {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        SpringApplication.run(CustomerApplication.class, args);
    }
}
