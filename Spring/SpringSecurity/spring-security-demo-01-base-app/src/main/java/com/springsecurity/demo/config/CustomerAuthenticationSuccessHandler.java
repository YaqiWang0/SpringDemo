package com.springsecurity.demo.config;

import com.springsecurity.demo.entity.User;
import com.springsecurity.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        System.out.println("\n\n In CustomAuthenticationSuccessHandler");
        String userName=authentication.getName();
        System.out.println("userName= "+userName);
        User user=userService.findByUserName(userName);

        HttpSession session=httpServletRequest.getSession();
        session.setAttribute("user",user);

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/");
    }
}
