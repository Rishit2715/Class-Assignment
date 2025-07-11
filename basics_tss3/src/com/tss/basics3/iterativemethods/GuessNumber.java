package com.tss.basics3.iterativemethods;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; 
        System.out.println("random generated number: "+randomNumber);
        
        String s1=  guessNumberGame(randomNumber);
        while(s1.equalsIgnoreCase("yes")) {
        	randomNumber = random.nextInt(100) + 1; 
            System.out.println("random generated number: "+randomNumber);

        	  s1 =  guessNumberGame(randomNumber);
        }
        System.out.println("game over");
       
	}
	
	public static String guessNumberGame(int randomNumber) {
		Scanner sc = new Scanner(System.in);

        int i=1;
		 while(i<=5) {
	        	System.out.println("guess a number");
	        	int num = sc.nextInt();
	        	
	        	if(num == randomNumber) {
	        		System.out.println("you won in "+i+" attempt");
	        		return "no";
	        	}
	        	else if (num > randomNumber) {
	        		System.out.println("sorry too high");
	        	}
	        	else {
	        		System.out.println("sorry too low");
	        	}
	        	i++;
	        	if(i>5) {
	        		System.out.println("you have useed all your chances");
	        		break;
	        	}
	        }
		 System.out.println("enter yes/no do you want to play again");
	        String play_game = sc.next();
	        return play_game;
	}

}
