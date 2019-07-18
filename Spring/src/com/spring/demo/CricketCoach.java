package com.spring.demo;

public class CricketCoach implements Coach {

    private FortuneService fortuneService;

    private String emailAddress;



    private String team;

    //create a no-arg constructor
    public CricketCoach() {
        System.out.println("no-arg construction");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    public void setFortuneService(FortuneService fortuneService) {
        System.out.println("inside the setter method");
        this.fortuneService = fortuneService;
    }

    public void setEmailAddress(String emailAddress) {
        System.out.println("inside setter method: setEmailAddress");
        this.emailAddress=emailAddress;
    }

    public void setTeam(String team) {
        System.out.println("inside setter method: setTeam");
        this.team = team;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTeam() {
        return team;
    }
}
