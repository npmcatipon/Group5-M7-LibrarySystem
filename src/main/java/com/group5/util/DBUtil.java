package com.group5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.group5.config.DBConfig;

public class DBUtil {

	private DBUtil() {}
		
		public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
		}
	
}
