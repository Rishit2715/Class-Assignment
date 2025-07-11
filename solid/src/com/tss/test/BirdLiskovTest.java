package com.tss.test;

import com.tss.model.OstrichLiskov;
import com.tss.model.PigeonLiskov;
import com.tss.model.SparrowLiskov;

public class BirdLiskovTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OstrichLiskov ostrich = new OstrichLiskov("brown");
		ostrich.walk();
		ostrich.getColour();

		SparrowLiskov sparrow = new SparrowLiskov("coffee");
		sparrow.fly();
		sparrow.getColour();

		PigeonLiskov pigeon = new PigeonLiskov("coffee");
		pigeon.fly();
		pigeon.getColour();
	}

}
