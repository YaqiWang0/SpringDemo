package com.springandhibernate.springdemo.aopdemo.dao;

import com.springandhibernate.springdemo.aopdemo.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDao {

    private String name;
    private String serviceCode;

    public List<Account> findAccounts(){
        List<Account> myAccounts=new ArrayList<>();

        //create sample accounts
        Account temp1=new Account("Jone","Silver");
        Account temp2=new Account("Madhu","Platinum");
        Account temp3=new Account("Luca","Gold");

        //add them to our accounts list
        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }

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
