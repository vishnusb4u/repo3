package com.vzw.vzhackers.textfreely.db;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OperationModular extends DBConnection {
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	public static String getTemplate(String operation) {

		String sql = "SELECT template FROM operation where operationid='"+operation+"'";
		String template = null;
		

		try {
            
          //Registering MYSQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");	
			//System.out.println("Connecting to database...");
			
			openDBConnection();
		
		    
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				//System.out.println(rs.getString("template"));
				template = rs.getString("template");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return template;
	}
	
}
