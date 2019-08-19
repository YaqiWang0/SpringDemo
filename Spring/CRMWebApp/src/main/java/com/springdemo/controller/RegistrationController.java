package com.springdemo.controller;

import com.springdemo.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebInitParam;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    private Logger logger =Logger.getLogger(getClass().getName());

    private Map<String,String> roles;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @PostConstruct
    protected void loadRoles(){

        roles=new LinkedHashMap<>();
        roles.put("ROLE_EMPLOYEE","Employee");
        roles.put("ROLE_MANAGER","Manager");
        roles.put("ROLE_ADMIN","Admin");
    }
    @GetMapping("/showRegistrationForm")
    public  String showMyLoginPage(Model model){
        model.addAttribute("crmUser",new CrmUser());

        model.addAttribute("roles",roles);
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("crmUser") CrmUser crmUser,
            BindingResult bindingResult,
            Model model){
        String userName= crmUser.getUserName();

        logger.info("Processing registration form for: "+userName);

        if(bindingResult.hasErrors()){
            model.addAttribute("crmUser",new CrmUser());
            model.addAttribute("registrationError","User name/password can not be empty");
            logger.warning("User name/password can not be empty");

            return "registration-form";
        }

        boolean userExists=doesUserExist(userName);

        if(userExists){
            model.addAttribute("crmUser",new CrmUser());
            model.addAttribute("registrationError","User name already exists.");

            logger.warning("User name already exists.");
            return "registration-form";
        }


        String encodedPassword=passwordEncoder.encode(crmUser.getPassword());

        encodedPassword="{bcrypt}"+encodedPassword;

        List<GrantedAuthority> authorities= AuthorityUtils.createAuthorityList();
        authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));

        // if the user selected role other than employee,
        // then add that one too (multiple roles)
        String formRole = crmUser.getFormRole();
        if (!formRole.equals("ROLE_EMPLOYEE")) {
            authorities.add(new SimpleGrantedAuthority(formRole));
        }

        User user=new User(userName,encodedPassword,authorities);

        userDetailsManager.createUser(user);

        logger.info("Successfully created user: "+userName );

        return "registration-confirmation";
    }

    private boolean doesUserExist(String userName) {
        logger.info("Checking if user exists: "+userName);

        boolean exists=userDetailsManager.userExists(userName);

        logger.info("User: "+userName+", exists: "+exists);
        return exists;
    }

}
