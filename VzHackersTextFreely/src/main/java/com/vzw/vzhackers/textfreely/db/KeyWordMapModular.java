package com.vzw.vzhackers.textfreely.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class KeyWordMapModular extends DBConnection {
	
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	public static Map<String,String> getKeyWordMap() {
		//Executing a query
	    //st = conn.createStatement();
		String sql = "SELECT word,keyname FROM key_word_map";
		//st.setString(1, mtnnum);
		
		Map<String,String> map = new HashMap<String,String>();

		try {
            
          //Registering MYSQL JDBC driver
			Class.forName("com.mysql.jdbc.Driver");			
			//System.out.println("Connecting to database...");
			
			openDBConnection();
		
		    
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				//System.out.println(rs.getString("word")+"-"+rs.getString("keyname"));
				map.put(rs.getString("word"),rs.getString("keyname"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return map;
	}
	
	public static void main(String[] args) {
		KeyWordMapModular.getKeyWordMap();
	}

}
