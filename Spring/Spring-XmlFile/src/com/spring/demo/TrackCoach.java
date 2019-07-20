package com.spring.demo;

public class TrackCoach implements Coach {

    private FortuneService fortuneService;

    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }

    @Override
    public String getDailyFortune() {
        return "Just do it: "+fortuneService.getFortune();
    }

    //  add an init method
    public void doMyStartUpStuff(){
        System.out.println("inside method startUpStuff");
    }

    //  add a destroy method
    public void doMyCleanUpMethod(){
        System.out.println("inside method cleanUpStuff");
    }

}
