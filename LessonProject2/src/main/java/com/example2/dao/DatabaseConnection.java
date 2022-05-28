package com.example2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example2.utils.PropertiesUtil;

public class DatabaseConnection {

	private static final String URL_KEY = "db.url";
	private static final String USERNAME_KEY = "db.username";
	private static final String PASSWORD_KEY = "db.password";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(PropertiesUtil.get(URL_KEY), PropertiesUtil.get(USERNAME_KEY), PropertiesUtil.get(PASSWORD_KEY));
	}
}
