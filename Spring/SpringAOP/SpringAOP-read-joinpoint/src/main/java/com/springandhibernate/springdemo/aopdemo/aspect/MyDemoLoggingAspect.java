package com.springandhibernate.springdemo.aopdemo.aspect;

import com.springandhibernate.springdemo.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Before("com.springandhibernate.springdemo.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){

        System.out.println("\n ==========>>> Executing @Before advice on addAccount()");

        //display the method signature
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: "+methodSignature);

        //display method arguments
        //get args
        Object[] args=joinPoint.getArgs();

        //loop thru args
        for(Object arg:args){
            System.out.println(arg);

            if(arg instanceof Account){
                //downcast and print Account specific stuff
                Account account=(Account) arg;

                System.out.println("account name: "+account.getName());
                System.out.println("account level: "+account.getLevel());
            }
        }

    }



}
