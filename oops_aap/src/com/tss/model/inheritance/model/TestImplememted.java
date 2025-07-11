package com.tss.model.inheritance.model;

public class TestImplememted implements ITestInterface {

	public void method1() {
		System.out.println("hello i am private inside implemented");
	}
	
	static void method2() {
		System.out.println("hello i am static inside implemented");
	}
	
	 public void method3() {
		System.out.println("hello i am default inside implemented");
	}
}
