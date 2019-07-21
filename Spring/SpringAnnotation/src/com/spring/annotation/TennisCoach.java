package com.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class TennisCoach implements Coach{



    @Autowired
    @Qualifier("randomFortuneService")
    private FortuneService fortuneService;

    @Value("${foo}")
    private String foo;

    public TennisCoach() {
        System.out.println(">>>>>inside the default constructor");
    }

    //    @Autowired
//    public TennisCoach(FortuneService fortuneService) {
//        this.fortuneService = fortuneService;
//    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        System.out.println("value is : "+foo);
        return fortuneService.getFortune();

    }

    //define my init method
    @PostConstruct
    public void doMyStartUpMethod(){
        System.out.println(">>>>>>>> inside of doMyStartUpMethod");
    }

    @PreDestroy
    public void doMyCleanUpMethod(){
        System.out.println(">>>>>>> inside of oMyCleanUpMethod");
    }

    //define my destroy method

//    @Autowired
//    public void setMyFortuneService(FortuneService fortuneService) {
//        System.out.println(">>>.inside the setter method");
//        this.fortuneService = fortuneService;
//    }

}
