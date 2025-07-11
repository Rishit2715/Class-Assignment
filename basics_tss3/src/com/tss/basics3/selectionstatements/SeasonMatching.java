package com.tss.basics3.selectionstatements;

import java.util.Scanner;

public class SeasonMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("enter first three letter of month");
		Scanner scanner = new Scanner(System.in);
        String month = scanner.next().toLowerCase(); 
        
        switch(month) {
        case "nov": case "dec": case "jan": case "feb": 
        	System.out.println("winter");
        	break;
        case "mar": case "apr": case "may": case "jun": 
        	System.out.println("summer");
        	break;
        case "jul": case "aug": case "sep": case "oct": 
        	System.out.println("monsoon");
        	break;
        default:
        	System.out.println("enter valid month range");
        		
        }

	}


}
