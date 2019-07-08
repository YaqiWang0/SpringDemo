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

        Account account=new Account();

        theAcountDAO.addAccount(account,true);

        theAcountDAO.doWork();

        membershipDAO.addSillyMember();

        membershipDAO.goToSleep();

        context.close();






    }
}
