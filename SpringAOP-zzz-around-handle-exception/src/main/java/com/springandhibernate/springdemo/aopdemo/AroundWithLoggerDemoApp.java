package com.springandhibernate.springdemo.aopdemo;

import com.springandhibernate.springdemo.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundWithLoggerDemoApp {
    
    private static Logger myLogger=Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService trafficFortuneService=context.getBean("trafficFortuneService",TrafficFortuneService.class);
        myLogger.info("\nMain program: AroundDemoApp");

        myLogger.info("Calling getFortune");

        String data=trafficFortuneService.getFortune();

        myLogger.info("\nMy fortune is: "+data);

        myLogger.info("Finished");
        //close the context
        context.close();

    }
}
