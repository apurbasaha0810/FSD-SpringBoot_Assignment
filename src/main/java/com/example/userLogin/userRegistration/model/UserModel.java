package com.example.userLogin.userRegistration.model;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class UserModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="USER_NAME")
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Id
    @Column(name="USER_ID")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name="PASSWORD")
    private String password;

}
