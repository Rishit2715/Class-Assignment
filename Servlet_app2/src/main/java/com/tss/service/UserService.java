package com.tss.service;

import java.util.List;
import com.tss.dao.UserDatabase;
import com.tss.model.User;

public class UserService {
    private UserDatabase userDatabase = new UserDatabase();

    public boolean registerUser(User user) {
        return userDatabase.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDatabase.getAllUsers();
    }

}
