package com.springdemo.rest.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.springdemo.rest.SimpleGrantedAuthorityDeserializer;
import com.springdemo.rest.model.Customer;
import com.springdemo.rest.model.User;
import com.springdemo.rest.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerServiceRestClientImpl implements CustomerService {

    private RestTemplate restTemplate;

    private String crmRestUrl;

    private Logger logger=Logger.getLogger(getClass().getName());
//    @PostConstruct
//    public void init(){
//        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("emma","abc"));
//    }

    @Autowired
    public CustomerServiceRestClientImpl(RestTemplate restTemplate, @Value("${crm.rest.url}") String crmRestUrl) {


        this.restTemplate = restTemplate;
        this.crmRestUrl=crmRestUrl;

        logger.info("Loaded property:  crm.rest.url=" + crmRestUrl);
    }

    HttpHeaders createHeaders(String username,String password){
        return new HttpHeaders(){
            {String auth=username+":"+password;
            byte[] encodedAuth= Base64.getEncoder().encode(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader="Basic "+new String(encodedAuth);
            set("Authorization",authHeader);
            }
        };
    }

    @Override
    public List<Customer> getCustomers() {
        logger.info("in getCustomers(): Calling REST API"+crmRestUrl);

        ResponseEntity<List<Customer>> responseEntity=restTemplate.exchange(crmRestUrl, HttpMethod.GET,null, new ParameterizedTypeReference<List<Customer>>() {
        });
        List<Customer> customers=responseEntity.getBody();

        logger.info("in getCustomers(): customers"+customers);
        return customers;
    }

    @Override
    public Customer getCustomer(int id) {
        logger.info("in getCustomer():Calling REST API"+crmRestUrl);

        Customer customer=restTemplate.getForObject(crmRestUrl+"/"+id,Customer.class);
        logger.info("in getCustomer(): customer="+customer);
        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {

        logger.info("in saveCustomer():Calling REST API "+crmRestUrl);

        int employeeId=customer.getId();

        if(employeeId==0){
            restTemplate.postForEntity(crmRestUrl,customer,String.class);
        }else {
            restTemplate.put(crmRestUrl,customer);
        }
        logger.info("in saveCustomer(): success");

    }



    @Override
    public void deleteCustomer(int id) {
        logger.info("in deleteCustomer(): Calling REST API "+crmRestUrl);

        restTemplate.delete(crmRestUrl+"/"+id);
        logger.info("in deleteCustomer(): deleted customer id="+id);

    }

    @Override
    public List<String> login(User user) {
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(user.getUsername(),user.getPassword()));
        ResponseEntity<List<String>> responseEntity=restTemplate.exchange("http://localhost:8079/api/login", HttpMethod.GET,null, new ParameterizedTypeReference<List<String>>() {
        });
        List<String> authorities=responseEntity.getBody();
        return authorities;
    }

    @Override
    public void addUser(CrmUser crmUser) {

        restTemplate.postForEntity("http://localhost:8079/api/addUser",crmUser,String.class);

    }
}
