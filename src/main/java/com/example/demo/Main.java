package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

//spring boot va scanner tous les packages à partir de celui-ci, donc il va trouver tous les composants spring (controller, service, repository, etc.)