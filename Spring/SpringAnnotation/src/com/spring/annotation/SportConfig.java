package com.spring.annotation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("sports.properties")
//@ComponentScan("")
public class SportConfig {


    //define bean for out sad fortune service
    @Bean
    public FortuneService sadFortueService(){
        return new SadFortuneService();
    }
    //define bean for our swim coach and inject dependency
    @Bean
    public Coach swimCoach(){
//        SwimCoach coach= new SwimCoach();
//        coach.setFortuneService(sadFortueService());
//        return coach;
        return  new SwimCoach(sadFortueService());
    }
}
