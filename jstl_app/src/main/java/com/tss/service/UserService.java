package com.tss.service;

import com.tss.dao.UserDao;
import com.tss.exception.AppException;
import com.tss.model.User;

public class UserService {
    private UserDao userDAO = new UserDao();

    public String registerEmployee(User user) throws AppException {
        if (userDAO.usernameExists(user.getUsername())) {
            return "Username already exists";
        }
        int newId = userDAO.registerEmployee(user);
        return (newId > 0) ? "SUCCESS" : "Error while registering employee";
    }
}
