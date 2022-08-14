package com.example.DioSpringM5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DioSpringM5Application {

    public static void main(String[] args) {
        SpringApplication.run(DioSpringM5Application.class, args);
    }

}
