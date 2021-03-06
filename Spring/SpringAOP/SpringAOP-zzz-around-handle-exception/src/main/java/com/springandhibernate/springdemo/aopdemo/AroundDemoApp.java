package com.springandhibernate.springdemo.aopdemo;

import com.springandhibernate.springdemo.aopdemo.dao.AccountDao;
import com.springandhibernate.springdemo.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AroundDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService trafficFortuneService=context.getBean("trafficFortuneService",TrafficFortuneService.class);
        System.out.println("\nMain program: AroundDemoApp");

        System.out.println("Calling getFortune");

        String data=trafficFortuneService.getFortune();

        System.out.println("\nMy fortune is: "+data);

        System.out.println("Finished");
        //close the context
        context.close();

    }
}
