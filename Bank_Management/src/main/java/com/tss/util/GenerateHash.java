package com.tss.util;

import org.mindrot.jbcrypt.BCrypt;

public class GenerateHash {
    public static void main(String[] args) {
        String password = "admin";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println("Password: " + password);
        System.out.println("Hash: " + hash);
        System.out.println("Length: " + hash.length());
        
        // Test if it matches
        System.out.println("Matches? " + BCrypt.checkpw(password, hash));
    }
}