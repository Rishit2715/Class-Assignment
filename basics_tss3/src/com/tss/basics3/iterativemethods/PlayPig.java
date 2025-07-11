package com.tss.basics3.iterativemethods;

import java.util.Random;
import java.util.Scanner;

public class PlayPig {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total_points=0, turn=1;
		
		while(turn<=5) {
			System.out.println("Turn: "+turn);
			
			total_points = getYourTurn(total_points);
			
			if(total_points>19) {
				System.out.println("You finished the game in "+turn+" turns!");
				return;
			}
			
			System.out.println("Total Score"+total_points);
			turn++;
		}
		System.out.println("Game Over");
	}

	public static int getYourTurn(int total_points) {
		int temp = total_points;
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		System.out.println("Enter Roll or Hold?");
		String roll_hold = sc.next();

		while(roll_hold.equalsIgnoreCase("roll"))
		{
	        int randomNumber = random.nextInt(6) + 1; 
	        System.out.println("Die : "+randomNumber);
	        if(randomNumber==1) {
	        	System.out.println("Turn over no score");
	        	return temp;
	        }
	        else {
	        	
	        	total_points+=randomNumber;
	        	if(total_points>19) {
		        	return total_points;
				}
	        }
	        
	    	System.out.println("Enter Roll or Hold?");
			roll_hold = sc.next(); 
		}
    	System.out.println("Score for turn: "+(total_points-temp));
		
		return total_points;
	}
}
