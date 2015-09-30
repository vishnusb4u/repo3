package com.vzw.vzhackers.textfreely.db;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class KeyOpGraphModular extends DBConnection {

	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	public static void getKeyOpGraph() {
		//Executing a query
	    //st = conn.createStatement();
		String root = "root";
		
		//st.setString(1, mtnnum);
		
		Map<String,String> map = new HashMap<String,String>();

		try {
			FileInputStream fis = new FileInputStream("db.properties");
            props.load(fis);
            
          //Registering MYSQL JDBC driver
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));		
			System.out.println("Connecting to database...");
			
			openDBConnection();
		
			String sql = "SELECT word,keyname FROM key_word_map";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("word")+"-"+rs.getString("keyname"));
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
	    
	}
}
