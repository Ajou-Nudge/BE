package com.vone.vone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.vone.vone.data"})
public class VoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoneApplication.class, args);
    }

}