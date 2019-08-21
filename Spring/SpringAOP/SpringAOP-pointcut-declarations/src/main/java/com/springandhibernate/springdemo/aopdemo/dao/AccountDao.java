package com.springandhibernate.springdemo.aopdemo.dao;

import com.springandhibernate.springdemo.aopdemo.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {

    public void addAccount(Account theAccount, boolean vipFlag){
        System.out.println(getClass()+": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public boolean doWork(){
        System.out.println(getClass()+" : doWork()");
        return false;
    }


}
