package com.spring.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

    public static void main(String[] args) {

        //read spring config file
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        //get the bean from spring container
        Coach coach=context.getBean("tennisCoach",Coach.class);

        Coach coach1=context.getBean("tennisCoach",Coach.class);
        //call a method on the bean
        System.out.println(coach==coach1);



        //close the context
        context.close();
    }
}
