package com.springandhibernate.springdemo.aopdemo;

import com.springandhibernate.springdemo.aopdemo.dao.AccountDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDao theAccountDAO=context.getBean("accountDao",AccountDao.class);

        //call method to find the accounts
        List<Account> theAccounts=null;
        try {
            //add a boolean flag to simulate exceptions
            boolean tripWire=false;
             theAccounts= theAccountDAO.findAccounts(tripWire);
        }catch (Exception e){
            System.out.println("\n\nMain Program ... caught exception: "+e);
        }

        //display the accounts
        System.out.println("\n\n Main Program: AfterThrowingDemoApp");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n");

        //close the context
        context.close();

    }
}
