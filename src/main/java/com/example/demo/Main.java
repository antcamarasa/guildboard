package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // With tomcat server
        SpringApplication.run(Main.class, args);

        // Without tomcat server
        //Program program = new Program();
        //program.testConnection();
    }

}
