package com.tss.basics3.selectionstatements;

import java.util.Random;

public class SwitchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random random = new Random();
        int randomNumber = random.nextInt(5) + 1; 
        System.out.println("random generated number: "+randomNumber);
        
        switch(randomNumber) {
        case 1:
        	System.out.println("One");
        	break;
        case 2:
        	System.out.println("Two");
        	break;
        case 3:
        	System.out.println("Three");
        	break;
        case 4:
        	System.out.println("Four");
        	break;
        case 5:
        	System.out.println("Five");
        	break;
        default:
        	System.out.println("out of five");
        		
        }

	}

}
