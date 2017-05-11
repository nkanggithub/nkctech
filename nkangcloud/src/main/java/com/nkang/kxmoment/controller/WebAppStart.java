package com.nkang.kxmoment.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;


@SpringBootApplication
public class WebAppStart extends SpringBootServletInitializer {
    public static void mains(String[] args) throws Exception {
        SpringApplication.run(WebAppStart.class, args);
    }
}
