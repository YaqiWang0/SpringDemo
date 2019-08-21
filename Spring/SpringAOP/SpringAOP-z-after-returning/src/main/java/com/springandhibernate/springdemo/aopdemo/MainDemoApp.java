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
        account.setName("Madhu");
        account.setLevel("Platinum");
        theAcountDAO.addAccount(account,true);
        theAcountDAO.doWork();

        //call the accountdao getter/setter methods
        theAcountDAO.setName("foobar");
        theAcountDAO.setServiceCode("silver");
        String name=theAcountDAO.getName();
        String serviceCode=theAcountDAO.getServiceCode();

        //call the membership business method
        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();

        //close the context
        context.close();

    }
}
