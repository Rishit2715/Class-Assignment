package com.tss.model.inheritance.model;

public interface ITestInterface {

	private void method1() {
		System.out.println("hello i am private");
	}
	
	static void method2() {
		System.out.println("hello i am static");
	}
	
	default void method3() {
		System.out.println("hello i am default");
		method1();
	}
}
