package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // With tomcat server
        //SpringApplication.run(DemoApplication.class, args);

        // Without tomcat server
        Program program = new Program();
        program.start();
    }

}
