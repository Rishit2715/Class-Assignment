package com.tss.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectionTest {

	// TODO Auto-generated method stub
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Class name = Class.forName("java.lang.String");

		Object obj = name.getDeclaredConstructor().newInstance();
		System.out.println(obj);

		Method methods[] = name.getMethods();

		for (Method method : methods) {

			System.out.print("\n" + method.getName() + "\t" + method.getParameterCount());
			Parameter parameters[] = method.getParameters();

			for (Parameter parameter : parameters) {
				System.out.print("\n" + parameter.getName() + "\t" + parameter.getType());
			}
		}
		Constructor constructors[] = name.getConstructors();

		for (Constructor constructor : constructors) {
			System.out.print("\n" + constructor.getName() + "\t" + constructor.getParameterCount());
			Parameter parameters[] = constructor.getParameters();
			for (Parameter parameter : parameters) {
				System.out.print("\n" + parameter.getName() + " \t" + parameter.getType());
			}
		}
	}

}
