package com.tss.test;

import com.tss.model.FestivalType;
import com.tss.model.FixedDeposit;

public class FixedDepositTest {
	public static void main(String[] args) {
		FixedDeposit fd1 = new FixedDeposit(1, "Ram", 4000, 6, FestivalType.NEWYEAR);
		FixedDeposit fd2 = new FixedDeposit(2, "Shyam", 5000, 5, FestivalType.DIWALI);

		System.out.println("Fixed Deposit 1 Interest: " + fd1.calculateSimpleInterest());
		System.out.println("Fixed Deposit 2 Interest: " + fd2.calculateSimpleInterest());
	}
}
