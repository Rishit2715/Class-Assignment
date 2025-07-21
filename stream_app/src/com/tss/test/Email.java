package com.tss.test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Email {
	
	    public static void main(String[] args) {
	        List<String> emails = List.of(
	            "rishit@gmail.com",
	            "jay@yahoo.com",
	            "kirtan@gmail.com",
	            "krish@gmail.com"
	        );

	        Set<String> extractDomains = emails.stream()
	            .map(email -> email.substring(email.indexOf("@") + 1)) 
	            .collect(Collectors.toSet()); 

	        System.out.println(extractDomains);
	    }
	}