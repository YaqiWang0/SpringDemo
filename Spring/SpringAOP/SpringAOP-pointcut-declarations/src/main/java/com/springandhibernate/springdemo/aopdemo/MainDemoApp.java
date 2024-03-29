package com.springandhibernate.springdemo.aopdemo;

import com.springandhibernate.springdemo.aopdemo.DemoConfig;
import com.springandhibernate.springdemo.aopdemo.dao.AccountDao;
import com.springandhibernate.springdemo.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDao theAcountDAO=context.getBean("accountDao",AccountDao.class);

        MembershipDAO membershipDAO=context.getBean("membershipDAO",MembershipDAO.class);

        //call the business method
        Account account=new Account();
        theAcountDAO.addAccount(account,true);
        theAcountDAO.doWork();

        //call the membership business method
        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();

        //close the context
        context.close();

    }
}
