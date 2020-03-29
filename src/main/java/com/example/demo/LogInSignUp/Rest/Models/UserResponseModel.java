package com.example.demo.LogInSignUp.Rest.Models;

import com.example.demo.LogInSignUp.persistence.models.Role;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class UserResponseModel implements Serializable {


    private static final long serialVersionUID = -5630863976654552168L;
    private Long id;
    private String password;
    private String username;
    private Set<Role> roles;

    public UserResponseModel() {
    }

    public UserResponseModel(Long id, String password, String username, Set<Role> roles) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponseModel)) return false;
        UserResponseModel that = (UserResponseModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(password, that.password) &&
                Objects.equals(username, that.username) &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, username, roles);
    }

    @Override
    public String toString() {
        return "UserResponseModel{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
