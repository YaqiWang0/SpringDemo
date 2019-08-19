package com.springsecurity.demo.user;

import com.springsecurity.demo.validation.FieldMatch;
import com.springsecurity.demo.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first="password",second="matchingPassword",message="The Password field must match")
})
public class CrmUser {

    @NotNull(message="is required")
    @Size(min=1 ,message="is required")
    private String userName;

    @NotNull(message = "is required")
    @Size(min=1, message="is required")
    private String password;

    @NotNull(message = "is required")
    @Size(min=1, message="is required")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min=1, message="is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min=1, message="is required")
    private  String lastName;

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min=1, message="is required")
    private String Email;


    private String formRole;

    public CrmUser() {
    }

    public String getFormRole() {
        return formRole;
    }

    public void setFormRole(String formRole) {
        this.formRole = formRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
