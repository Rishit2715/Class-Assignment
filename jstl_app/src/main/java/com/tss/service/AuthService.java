package com.tss.service;

import com.tss.dao.UserDao;
import com.tss.exception.AppException;
import com.tss.model.User;

public class AuthService {
    private UserDao dao = new UserDao();

    public User login(String username, String password) throws AppException {
        // in production: validate input, hash password, timing-safe compare
        return dao.findByUsernameAndPassword(username, password);
    }

    public User getById(int id) throws AppException {
        return dao.findById(id);
    }
}
