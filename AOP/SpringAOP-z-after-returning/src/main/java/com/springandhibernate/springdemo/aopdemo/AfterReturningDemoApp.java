package com.springandhibernate.springdemo.aopdemo;

import com.springandhibernate.springdemo.aopdemo.Account;
import com.springandhibernate.springdemo.aopdemo.DemoConfig;
import com.springandhibernate.springdemo.aopdemo.dao.AccountDao;
import com.springandhibernate.springdemo.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDao theAccountDAO=context.getBean("accountDao",AccountDao.class);

        //call method to find the accounts
        List<Account> theAccounts=theAccountDAO.findAccounts();

        //display the accounts
        System.out.println("\n\n Main Program: AfterReturningDemoApp");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n");

        //close the context
        context.close();

    }
}
