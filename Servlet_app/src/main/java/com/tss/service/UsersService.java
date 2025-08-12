package com.tss.service;

import com.tss.dao.UserDAO;

public class UsersService {
    private UserDAO userDAO;

    public UsersService() {
    	userDAO = new UserDAO();
    }

    public boolean validateUser(String username, String password, String role) {
        return userDAO.checkUser(username, password, role);
    }
}
