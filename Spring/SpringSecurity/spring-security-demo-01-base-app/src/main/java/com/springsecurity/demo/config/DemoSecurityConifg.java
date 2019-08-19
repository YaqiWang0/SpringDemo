package com.springsecurity.demo.config;

import com.springsecurity.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConifg extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource securityDataSource;

    @Autowired
    private CustomerAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//                .withUser(users.username("mary").password("test123").roles("MANAGER","EMPLOYEE"))
//                .withUser(users.username("susan").password("test123").roles("ADMIN","EMPLOYEE"));

       // auth.jdbcAuthentication().dataSource(securityDataSource);

        auth.authenticationProvider(authenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/leaders/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
        .and().logout().permitAll()
        .and().exceptionHandling()
        .accessDeniedPage("/access-denied");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


}
