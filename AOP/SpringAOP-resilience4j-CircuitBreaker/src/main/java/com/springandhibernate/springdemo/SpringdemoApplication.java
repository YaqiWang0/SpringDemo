package com.springandhibernate.springdemo;

import com.springandhibernate.springdemo.aopdemo.AfterReturningDemoApp;
import com.springandhibernate.springdemo.aopdemo.DemoConfig;
import com.springandhibernate.springdemo.aopdemo.service.TrafficFortuneService;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringdemoApplication implements CommandLineRunner {

    @Autowired
    private TrafficFortuneService service;

    public static void main(String[] args) {

        SpringApplication.run(SpringdemoApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {

        service.getFortune();
    }
}
