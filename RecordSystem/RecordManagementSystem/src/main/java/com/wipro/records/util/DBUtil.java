package com.wipro.records.util;

import java.sql.Connection;

import java.sql.DriverManager;

public class DBUtil {

	public static Connection getDBConnection() throws Exception {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    String url = "jdbc:oracle:thin:@localhost:1521:xe";
	    String username = "IBRAHIM";   // must match Oracle user
	    String password = "ibrahim123"; // must match Oracle password
	    return DriverManager.getConnection(url, username, password);
	}
}