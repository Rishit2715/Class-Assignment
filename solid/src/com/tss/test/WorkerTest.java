package com.tss.test;

import com.tss.model.Labour;
import com.tss.model.Robot;

public class WorkerTest {

	public static void main(String[] args) {
		Labour labour = new Labour();
		labour.startWork();
		labour.drink();
		labour.eat();
		labour.stopWork();

		Robot robot = new Robot();
		robot.startWork();
		robot.drink();
		robot.eat();
		robot.stopWork();

	}
}
