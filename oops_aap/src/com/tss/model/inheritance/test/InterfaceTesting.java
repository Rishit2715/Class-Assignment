package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.TestImplememted;
import com.tss.model.inheritance.model.ITestInterface;

public class InterfaceTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestImplememted test = new TestImplememted();
		
		test.method1();
		ITestInterface.method2();
		test.method3();

	}

}
