package com.springandhibernate.springdemo.aopdemo;

import com.springandhibernate.springdemo.aopdemo.DemoConfig;
import com.springandhibernate.springdemo.aopdemo.dao.AccountDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDao theAcountDAO=context.getBean("accountDao",AccountDao.class);

        theAcountDAO.addAccount();

        System.out.println("\n let's call it again!\n");

        theAcountDAO.addAccount();

        context.close();






    }
}
