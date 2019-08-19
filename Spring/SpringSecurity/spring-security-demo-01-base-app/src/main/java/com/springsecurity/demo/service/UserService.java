package com.springsecurity.demo.service;

import com.springsecurity.demo.entity.User;
import com.springsecurity.demo.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(CrmUser crmUser);

}
