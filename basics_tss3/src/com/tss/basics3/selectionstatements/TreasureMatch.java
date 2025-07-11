package com.tss.basics3.selectionstatements;

import java.util.Scanner;

public class TreasureMatch {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter whether you want to go left or right: ");
		String left_right = sc.next();
		if(left_right.equalsIgnoreCase("left")){
			System.out.println("Enter whether you want to swim or wait: ");
			String swim_wait = sc.next();
			
			if(swim_wait.equalsIgnoreCase("wait")) {
				System.out.println("Enter which door you want to enter blue or red or yellow: ");
				String blue_red_yellow = sc.next();
				
				if(blue_red_yellow.equalsIgnoreCase("blue")) {
					System.out.println("Eaten by beasts. Game over");
				}
				
				else if(blue_red_yellow.equalsIgnoreCase("red")) {
					System.out.println("Burned by fire. Game over");
				}
				
				
				else if(blue_red_yellow.equalsIgnoreCase("yellow")) {
					System.out.println("You Win");
				}
				else {
					System.out.println("Game Over");
				}
			}
			else {
				System.out.println("Attacked by trout. Game over");		
			}
		}
		else {
			System.out.println("Fall into a Hole. Game over");		
		}	
	}
	

}
