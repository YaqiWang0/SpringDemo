package com.springandhibernate.springdemo.aopdemo.dao;

import com.springandhibernate.springdemo.aopdemo.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {

    private String name;
    private String serviceCode;

    public void addAccount(Account theAccount, boolean vipFlag){
        System.out.println(getClass()+": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public boolean doWork(){
        System.out.println(getClass()+" : doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass()+" : it get getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass()+" : it get setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass()+" : it get getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass()+" : it get setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
