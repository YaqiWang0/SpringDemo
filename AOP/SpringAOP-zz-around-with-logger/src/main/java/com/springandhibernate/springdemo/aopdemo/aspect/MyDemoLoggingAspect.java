package com.springandhibernate.springdemo.aopdemo.aspect;

import com.springandhibernate.springdemo.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    
    private static Logger logger=Logger.getLogger(MyDemoLoggingAspect.class.getName());
    @Around("execution(* com.springandhibernate.springdemo.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        //print out method we are advising on
        String method=proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n=======>>> Executing @Around on method: "+method);

        //get begin timestamp
        long begin=System.currentTimeMillis();

        //now,let's execute the method
        Object result=proceedingJoinPoint.proceed();

        //get end timestamp
        long end=System.currentTimeMillis();

        //compute duration and display it
        long duration=end-begin;
        logger.info("\n=======> Duration: "+duration/1000.0+ " seconds");


        return result;
    }

    @After("execution(* com.springandhibernate.springdemo.aopdemo.dao.AccountDao.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){

        //print out which method we are advising on
        String method=joinPoint.getSignature().toShortString();
        logger.info("\n=======>>> Executing @After (finally) on method: "+method);

    }


    @AfterThrowing(
            pointcut = "execution(* com.springandhibernate.springdemo.aopdemo.dao.AccountDao.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,Throwable theExc){

        //print out which method we are advising on
        String method=joinPoint.getSignature().toShortString();
        logger.info("\n=======>>> Executing @AfterThrowing on method: "+method);
        

        //log the exception
        logger.info("\n=======>>> exception is : "+theExc);
    }





    @AfterReturning(
            pointcut = "execution(* com.springandhibernate.springdemo.aopdemo.dao.AccountDao.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result){

        //print out which method we are advising on
        String method =joinPoint.getSignature().toShortString();
        logger.info("\n=======>>> Executing @AfterReturning on method: "+method);

        //print out the results of the method call
        logger.info("\n=======>>> result is : "+result);

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

        logger.info("\n ==========>>> Executing @Before advice on addAccount()");

        //display the method signature
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        logger.info("Method: "+methodSignature);

        //display method arguments
        //get args
        Object[] args=joinPoint.getArgs();

        //loop thru args
        for(Object arg:args){
            logger.info(arg.toString());

            if(arg instanceof Account){
                //downcast and print Account specific stuff
                Account account=(Account) arg;

                logger.info("account name: "+account.getName());
                logger.info("account level: "+account.getLevel());
            }
        }

    }



}
