package com.springandhibernate.springdemo.aopdemo;

import com.springandhibernate.springdemo.aopdemo.service.TrafficFortuneService;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {
    
    private static org.slf4j.Logger myLogger= LoggerFactory.getLogger(AroundHandleExceptionDemoApp.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService trafficFortuneService=context.getBean("trafficFortuneService",TrafficFortuneService.class);
        myLogger.info("\nMain program: AroundDemoApp");

        myLogger.info("Calling getFortune");

        boolean tripWire=true;
        String data=trafficFortuneService.getFortune();

        myLogger.info("\nMy fortune is: "+data);

        myLogger.info("Finished");

        //close the context
        context.close();

    }
}
