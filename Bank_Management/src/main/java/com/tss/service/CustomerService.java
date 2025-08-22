// File: src/com/tss/service/CustomerService.java
package com.tss.service;

import com.tss.dao.CustomerDAO;
import com.tss.model.Customer;
import com.tss.util.PasswordUtil;

/**
 * Service class for handling customer business logic
 * - Registration with inactive status
 * - Login with status validation
 */
public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    /**
     * Registers a new customer
     * - Checks if username or email already exists
     * - Hashes password
     * - Sets role = 'customer', status = 'inactive'
     * @return true if registration successful
     */
    public boolean registerCustomer(String username, String password, String name,
                                    String email, String phone, String address) {
        
        // Check for duplicates
        if (customerDAO.isUsernameOrEmailExists(username, email)) {
            System.out.println("Registration failed: Username or email already exists: " + username);
            return false;
        }

        // Hash password
        String hashedPassword = PasswordUtil.hashPassword(password);
        if (hashedPassword == null) {
            System.out.println("Registration failed: Password hashing failed");
            return false;
        }

        // Create customer with default role and inactive status
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPasswordHash(hashedPassword);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setRole("customer");
        customer.setStatus("inactive");  // Must be approved by admin

        System.out.println("Registering customer: " + username + " with status: " + customer.getStatus());

        // Save to database
        boolean success = customerDAO.registerCustomer(customer);
        if (success) {
            System.out.println("✅ Customer registered successfully: " + username);
        } else {
            System.out.println("❌ Customer registration failed: " + username);
        }

        return success;
    }

    /**
     * Validates customer login
     * - Checks username exists
     * - Verifies password
     * - Ensures customer is active
     * @return Customer object if login successful, null otherwise
     */
    public Customer login(String username, String password) {
        System.out.println("Login attempt: " + username);

        Customer customer = customerDAO.findByUsername(username);
        if (customer == null) {
            System.out.println("❌ Login failed: User not found");
            return null;
        }

        System.out.println("Found customer: " + customer.getName());
        System.out.println("Stored hash: " + customer.getPasswordHash());
        System.out.println("Input password: " + password);

        // Verify password
        boolean match = PasswordUtil.checkPassword(password, customer.getPasswordHash());
        System.out.println("Password match: " + match);

        if (!match) {
            System.out.println("❌ Login failed: Invalid password for " + username);
            return null;
        }

        // Check account status
        String status = customer.getStatus();
        if ("inactive".equals(status)) {
            System.out.println("❌ Login failed: Account is inactive (pending approval): " + username);
            return null;
        }
        if ("suspended".equals(status)) {
            System.out.println("❌ Login failed: Account is suspended: " + username);
            return null;
        }
        if (!"active".equals(status)) {
            System.out.println("❌ Login failed: Unknown status '" + status + "' for " + username);
            return null;
        }

        System.out.println("✅ Login successful: " + customer.getName() + " (" + customer.getUsername() + ")");
        return customer;
    }
}