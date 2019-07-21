package com.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

    public static void main(String[] args) {

        //read spring config file
         AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SportConfig.class);

        //get the bean from spring container
        SwimCoach coach=context.getBean("swimCoach",SwimCoach.class);

        //call a method on the bean
        System.out.println(coach.getDailyWorkout());


        System.out.println(coach.getDailyFortune());

        System.out.println(coach.getEmail());

        System.out.println(coach.getTeam());
        //close the context
        context.close();
    }
}
