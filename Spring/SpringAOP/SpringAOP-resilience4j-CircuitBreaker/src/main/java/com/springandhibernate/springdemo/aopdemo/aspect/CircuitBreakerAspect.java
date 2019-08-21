package com.springandhibernate.springdemo.aopdemo.aspect;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component
public class CircuitBreakerAspect {

    @Around("execution(* com.springandhibernate.springdemo.aopdemo.service.GetFortuneforCircuit.getFortune2(..))")
    public Object circuitBreakerAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        long startTime = System.currentTimeMillis();

        CircuitBreakerConfig circuitBreakerConfig=CircuitBreakerConfig.custom()
                .failureRateThreshold(10)
                .waitDurationInOpenState(Duration.ofMillis(5000))
                .ringBufferSizeInHalfOpenState(5)
                .ringBufferSizeInClosedState(5)
                .recordFailure(e->true)
                .recordExceptions(RuntimeException.class)
                .ignoreExceptions()
                .build();

        CircuitBreakerRegistry circuitBreakerRegistry=CircuitBreakerRegistry.of(circuitBreakerConfig);

        CircuitBreaker circuitBreaker=circuitBreakerRegistry.circuitBreaker("FORTUNE");

        Object result=null;
        try {
            //now,let's execute the method

            result=proceedingJoinPoint.proceed();
            long end=System.currentTimeMillis();
            circuitBreaker.onSuccess(end-startTime);
            return result;
        }catch (Exception e){
            long end=System.currentTimeMillis();
            //log the exception
            circuitBreaker.onError(end-startTime,e);

            //rethrow the exception
        }
return "error";

    }
}
