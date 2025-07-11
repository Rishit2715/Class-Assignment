package com.tss.test;

import com.tss.model.Bird;
import com.tss.model.Ostrich;
import com.tss.model.Pigeon;
import com.tss.model.Sparrow;

public class BirdTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Bird bird = new Bird();
		bird = new Sparrow();
		bird.fly();

		bird = new Pigeon();
		bird.fly();

		bird = new Ostrich();
		bird.fly();
	}

}
