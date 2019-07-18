package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        CricketCoach cricketCoach=context.getBean("CricketCoach",CricketCoach.class);

        System.out.println(cricketCoach.getEmailAddress());
        System.out.println(cricketCoach.getTeam());
    }



}
