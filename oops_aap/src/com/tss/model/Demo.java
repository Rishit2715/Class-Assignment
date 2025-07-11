package com.tss.model;

public class Demo {
	
	private int a;
	private int b;
	private static int c;
	
	public Demo() {
		System.out.println("inside demo constructor");
	}
	
	public void increment() {
		a++;
		b++;
		c++;
	}
	
	public void display() {
		System.out.println("a: "+a);
		System.out.println("b: "+b);
		System.out.println("c: "+c);

	}
	
	 {
		System.out.println("indide Demo ");
	}
	static {
		System.out.println("indide Demo static ");
	}

}
