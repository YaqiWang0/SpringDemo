package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {
    public static void main(String[] args) {

        //load the spring configuration file
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        //retrieve bean from spring container
        Coach coach=context.getBean("myCoach",Coach.class);
        //close thecontext
        System.out.println(coach.getDailyFortune());

        Coach coach1=context.getBean("CricketCoach",Coach.class);
        System.out.println(coach1.getDailyFortune());
        context.close();
    }
}
