package com.tss.singleton.test;

import com.tss.singleton.model.DatabaseConnection;

public class DemoTest {
	
	public static void main(String []args) {
		
		DatabaseConnection connection = DatabaseConnection.dbConnection();
		System.out.println(connection.hashCode());
		DatabaseConnection connection1 = DatabaseConnection.dbConnection();
		System.out.println(connection1.hashCode());
		DatabaseConnection connection2 = DatabaseConnection.dbConnection();
		System.out.println(connection2.hashCode());
	}

}
