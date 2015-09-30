package com.vzw.vzhackers.textfreely.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	
	static Connection conn = null;
	
	//method to connect to mysql Database
		public static void openDBConnection()
		{
			try{
				Class.forName("com.mysql.jdbc.Driver");		
				//System.out.println("Connecting to database...");
					//Opening a connection
					conn = DriverManager.getConnection("jdbc:mysql://113.128.163.34:3306/test_database",
												   	   "sqluser","sqluserpw");
					//System.out.println("DB hit successful");
				}catch(SQLException sqle){
					sqle.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}//end of method openDBConnection
}
