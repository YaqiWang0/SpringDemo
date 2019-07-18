package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

    public static void main(String[] args) {

        //load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanLifeCycle-ApplicationContext.xml");


        //retrieve bean from spring container
        Coach coach = context.getBean("myCoach", Coach.class);


        System.out.println(coach.getDailyFortune());


        context.close();

    }
}
