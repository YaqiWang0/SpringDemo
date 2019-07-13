package com.springandhibernate.springdemo.aopdemo;

import com.springandhibernate.springdemo.aopdemo.service.GetFortuneforCircuit;
import com.springandhibernate.springdemo.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class CircuitBreakerDemoApp {
    
    private static Logger myLogger=Logger.getLogger(CircuitBreakerDemoApp.class.getName());

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

       GetFortuneforCircuit getForturneforCircuit=context.getBean("getFortuneforCircuit",GetFortuneforCircuit.class);


       getForturneforCircuit.getFortune2();
        //close the context
        context.close();

    }
}
