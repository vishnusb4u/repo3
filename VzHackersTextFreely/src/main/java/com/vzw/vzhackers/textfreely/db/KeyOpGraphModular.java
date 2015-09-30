package com.vzw.vzhackers.textfreely.db;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vzw.vzhackers.textfreely.core.KeyOpGraph;

public class KeyOpGraphModular extends DBConnection {

	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	public static KeyOpGraph getKeyOpGraph() {

		String root = "root";
		
		
		Map<String,String> map = new HashMap<String,String>();
		KeyOpGraph root1 = null;

		try {
            
          //Registering MYSQL JDBC driver
			Class.forName("com.mysql.jdbc.Driver");		
			System.out.println("Connecting to database...");
			
			openDBConnection();
		
			String sql = "SELECT keyname,parentkeyname,operationid FROM key_op_graph where parentkeyname is null";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				
				root1 = new KeyOpGraph();
				root1.setKeyId(rs.getString("keyname"));
				root1.setOperation(rs.getString("operationId"));
				root1.setParentKey(rs.getString("parentKeyName"));
				root1.setChildGraph(getChild(rs.getString("keyname")));
				System.out.println(rs.getString("parentkeyname")+"-"+rs.getString("keyname"));
//				map.put(rs.getString("word"),rs.getString("keyname"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root1;
	    
	}
	
	private static List<KeyOpGraph> getChild(String root) {
		List<KeyOpGraph> childNode = new ArrayList<KeyOpGraph>();
		
		String sql = "SELECT keyname,parentkeyname,operationid FROM key_op_graph where parentkeyname ='"+root+"'";
		try {
			st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			
			KeyOpGraph root1 = new KeyOpGraph();
			root1.setKeyId(rs.getString("keyname"));
			root1.setOperation(rs.getString("operationId"));
			root1.setParentKey(rs.getString("parentKeyName"));
			root1.setChildGraph(getChild(rs.getString("keyname")));
			childNode.add(root1);
//			System.out.println(rs.getString("parentkeyname")+"-"+rs.getString("keyname"));
//			map.put(rs.getString("word"),rs.getString("keyname"));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(childNode.isEmpty()) {
			System.out.println("leaf-"+root);
		} else {
			System.out.println("root-"+root);
		}
		return childNode;
	}

}
