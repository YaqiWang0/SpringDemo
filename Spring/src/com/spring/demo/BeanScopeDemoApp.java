package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

    public static void main(String[] args) {

        //load the spring configuration file
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beanScope-ApplicationContext.xml");


        //retrieve bean from spring container
        Coach coach=context.getBean("myCoach",Coach.class);

        Coach coach1=context.getBean("myCoach",Coach.class);
        //check if they are the same bean

        boolean result=(coach==coach1);
        context.close();
    }

}
