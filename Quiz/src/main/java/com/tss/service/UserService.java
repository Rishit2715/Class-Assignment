package com.tss.service;

import com.tss.dao.UserDAO;
import com.tss.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean register(User user) {
        return userDAO.registerUser(user);
    }
    
    public boolean loginUser(User user) {
        return userDAO.validateUser(user);
    }
    
    public User getUserByCredentials(String username, String password) {
        return userDAO.findByUsernameAndPassword(username, password);
    }

	public User getUserByUsernameAndPassword(String username, String password) {
    return userDAO.getUserByUsernameAndPassword(username, password);
}


}
