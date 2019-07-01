package com.example.userLogin.userRegistration.dao;

import com.example.userLogin.userRegistration.model.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class userDao implements userOperations {

    Session session;
    public userDao(Session session) {
        this.session=session;
    }

    @Override
    public String insertUser(UserModel user) {
        Transaction tx= session.beginTransaction();
        session.save(user);
        tx.commit();
        return "inserted";
    }

    @Override
    public UserModel getUserById(int id) {
        UserModel user = new UserModel();
        Transaction tx = session.beginTransaction();
         session.byId(UserModel.class);
        tx.commit();
        return user;
    }

    @Override
    public void updatePassword(UserModel user)
    {
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();


    }

}
