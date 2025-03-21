package com.geektext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Automatically scans sub-packages: controllers, models, repositories
public class GeekTextApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekTextApplication.class, args);
    }
}
