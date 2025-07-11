package com.tss.test;

import com.tss.model.LoaderSegregation;
import com.tss.model.RobotSegregation;

public class WorkeeSegregationTest {

	public static void main(String[] args) {
		LoaderSegregation labour = new LoaderSegregation();
		labour.startWork();
		labour.rest();
		labour.eat();
		labour.stopWork();

		RobotSegregation robot = new RobotSegregation();
		robot.startWork();
		robot.stopWork();

	}
}
