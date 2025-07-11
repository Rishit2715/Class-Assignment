package com.tss.singleton.model;

public class DatabaseConnection {

	private static DatabaseConnection connection = null;

	private DatabaseConnection() {

	}

	public static DatabaseConnection dbConnection() {
		if (connection == null) {
			connection = new DatabaseConnection();
		}
		return connection;
	}
}
