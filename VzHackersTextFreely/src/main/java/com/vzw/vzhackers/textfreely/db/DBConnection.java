package com.vzw.vzhackers.textfreely.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DBConnection {
	
	static Connection conn = null;
	static Properties props = new Properties();
	
	//method to connect to mysql Database
		public static void openDBConnection()
		{
			try{
					//Opening a connection
					conn = DriverManager.getConnection("jdbc:mysql://113.128.163.34:3306/test_database",
												   	   "sqluser","sqluserpw");
					System.out.println("DB hit successful");
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
		}//end of method openDBConnection
}
