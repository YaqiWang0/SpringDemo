package com.springandhibernate.springdemo.aopdemo.service;

import org.springframework.stereotype.Component;

@Component
public class GetFortuneforCircuit {
    public String getFortune2(){
        for(int i=0;i<5;i++){
            if(i == 0)
                throw new RuntimeException("error occurs!!");
        }
        return "good";
    }
}
