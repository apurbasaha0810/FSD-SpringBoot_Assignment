package com.example.userLogin.userRegistration.dao;

import com.example.userLogin.userRegistration.model.UserModel;

public interface userOperations {

    public String insertUser(UserModel user);
    public UserModel getUserById(int id);
    public void updatePassword(UserModel user);
}
