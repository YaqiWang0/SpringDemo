package com.springandhibernate.springdemo.aopdemo.aspect;

import com.springandhibernate.springdemo.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @After("execution(* com.springandhibernate.springdemo.aopdemo.dao.AccountDao.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){

        //print out which method we are advising on
        String method=joinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @After (finally) on method: "+method);

    }


    @AfterThrowing(
            pointcut = "execution(* com.springandhibernate.springdemo.aopdemo.dao.AccountDao.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,Throwable theExc){

        //print out which method we are advising on
        String method=joinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @AfterThrowing on method: "+method);
        

        //log the exception
        System.out.println("\n=======>>> exception is : "+theExc);
    }





    @AfterReturning(
            pointcut = "execution(* com.springandhibernate.springdemo.aopdemo.dao.AccountDao.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result){

        //print out which method we are advising on
        String method =joinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @AfterReturning on method: "+method);

        //print out the results of the method call
        System.out.println("\n=======>>> result is : "+result);

        //let's post-process teh data .. let's modify it :-)
        //convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        //loop through the accounts

        for(Account account:result) {
            //get uppercase version of name
            String theUpperName=account.getName().toUpperCase();

            //update the name on the account
            account.setName(theUpperName);
        }
    }

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
