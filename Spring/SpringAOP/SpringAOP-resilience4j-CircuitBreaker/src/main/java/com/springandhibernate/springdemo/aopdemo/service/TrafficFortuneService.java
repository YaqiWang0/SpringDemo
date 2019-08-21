package com.springandhibernate.springdemo.aopdemo.service;

import com.springandhibernate.springdemo.aopdemo.Account;
import com.springandhibernate.springdemo.aopdemo.TestC;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TrafficFortuneService {
    public TrafficFortuneService() {
    }

    @Autowired
    public TestC test;

    public String getFortune(){

        //simulate a delay

        String name=test.getKey();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //return a fortune
        return "Expect heavy traffic this morning";
    }

    public String getFortune(boolean tripWire) {
        if(tripWire) {
            throw new RuntimeException("Major accident! Highway is closed!");
        }

        return getFortune();
    }
}
