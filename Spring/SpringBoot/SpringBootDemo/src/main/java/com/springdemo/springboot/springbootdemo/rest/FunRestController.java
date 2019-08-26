package com.springdemo.springboot.springbootdemo.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class FunRestController {

    //expose "/" that return "HelloWorld"

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/")
    public String sayHello(){
        return "Hello World! Time on server is "+ LocalDateTime.now();
    }

    @GetMapping("/teaminfo")
    public String teamInfo(){
        return "Coach name"+coachName+", Team name:"+teamName;
    }

    @GetMapping("/workout")
    public String workOut(){
        return "workout";
    }

    @GetMapping("/fortune")
    public String fortune(){
        return "fortune";
    }

}
