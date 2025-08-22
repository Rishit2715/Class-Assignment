package com.tss.model;
//File: com.tss.model.Customer
public class Customer {
 private int id;
 private String username;
 private String passwordHash;  // Renamed from password
 private String name;
 private String email;
 private String phone;
 private String address;
 private String role;
 private String status;

 public String getStatus() {
     return status;
 }

 public void setStatus(String status) {
     this.status = status;
 }

 // Constructors
 public Customer() {}

 public Customer(String username, String passwordHash, String name, String email,
                 String phone, String address, String role) {
     this.username = username;
     this.passwordHash = passwordHash;
     this.name = name;
     this.email = email;
     this.phone = phone;
     this.address = address;
     this.role = role;
 }

 // Getters and Setters
 public int getId() { return id; }
 public void setId(int id) { this.id = id; }

 public String getUsername() { return username; }
 public void setUsername(String username) { this.username = username; }

 public String getPasswordHash() { return passwordHash; }
 public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

 public String getName() { return name; }
 public void setName(String name) { this.name = name; }

 public String getEmail() { return email; }
 public void setEmail(String email) { this.email = email; }

 public String getPhone() { return phone; }
 public void setPhone(String phone) { this.phone = phone; }

 public String getAddress() { return address; }
 public void setAddress(String address) { this.address = address; }

 public String getRole() { return role; }
 public void setRole(String role) { this.role = role; }
}