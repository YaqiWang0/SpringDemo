package com.springsecurity.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
    //set up variable to hold the properties

    @Autowired
    private Environment env;

    private Logger logger=Logger.getLogger(getClass().getName());

    //define a bean for viewResolver
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }



    //define a bean for out security datasource
    @Bean
    public DataSource securityDataSource(){

        //create connection poll
        ComboPooledDataSource securityDataSource=new ComboPooledDataSource();

        //set jdbc driver class
        try {
            securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw  new RuntimeException(e);
        }

        //logs the connection props
        logger.info("=====>>jdbc.url="+env.getProperty("jdbc.driver"));
        logger.info("=====>>jdbc.user="+env.getProperty("jdbc.user"));

        //set database connection props
        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSource.setUser(env.getProperty("jdbc.user"));
        securityDataSource.setPassword(env.getProperty("jdbc.password"));

        //set connection pool props
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));


        return securityDataSource;
    }


    private int getIntProperty(String propName){
        String propVal=env.getProperty(propName);
        int intPropval=Integer.parseInt(propVal);
        return intPropval;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){

        HibernateTransactionManager txManager=new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    private Properties getHibernateProperties(){
        Properties prop=new Properties();
        prop.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
        prop.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));

        return prop;
    }



}
