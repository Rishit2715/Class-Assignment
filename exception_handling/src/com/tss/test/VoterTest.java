package com.tss.test;
import com.tss.model.Voter;

public class VoterTest {
	public static void main(String []args) {
		try
		{
			Voter voter = new Voter("Rishit",210,19);
			System.out.println(voter);
			
			Voter voter2 = new Voter("Rishit",310,10);
			System.out.println(voter2);
			
		}
		catch(Exception exception)
		{
			System.out.println(exception);
		}
	}
}
