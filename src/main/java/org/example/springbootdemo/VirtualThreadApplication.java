package org.example.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
public class VirtualThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualThreadApplication.class, args);
    }

}
