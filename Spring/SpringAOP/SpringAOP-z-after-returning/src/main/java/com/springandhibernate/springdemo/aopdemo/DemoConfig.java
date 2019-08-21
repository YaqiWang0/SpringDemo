package com.springandhibernate.springdemo.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.springandhibernate.springdemo.aopdemo")
public class DemoConfig {

}
