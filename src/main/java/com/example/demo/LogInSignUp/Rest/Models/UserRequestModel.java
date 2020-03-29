package com.example.demo.LogInSignUp.Rest.Models;

import com.example.demo.LogInSignUp.persistence.models.Role;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class UserRequestModel implements Serializable {


    private static final long serialVersionUID = 6146675581652589579L;
    private String username;
    private String password;
    private Set<Role> roles;
    private String passwordConfirm;

    public UserRequestModel() {
    }

    public UserRequestModel(String username, String password, Set<Role> roles, String passwordConfirm) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.passwordConfirm = passwordConfirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequestModel)) return false;
        UserRequestModel that = (UserRequestModel) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(passwordConfirm, that.passwordConfirm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, roles, passwordConfirm);
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }
}
