package com.vzw.vzhackers.textfreely.db;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PlanDetailsInfo extends DBConnection{
	
	static FileInputStream fis = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;

	//method to execute the Billing Query and list out to the console
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String runQuery(String mtnnum, String template)
	{	
		try{
				//Executing a query
			    //st = conn.createStatement();
				String sql = "SELECT C_INFO.custId, C_INFO.custName, C_BILL.amount, C_PLAN.PlanId, C_PLAN.isActive, P_DET.planName, P_DET.dataBw, P_DET.sms, P_DET.planPrice, P_DET.voiceMin  FROM CUSTOMER_INFO C_INFO INNER JOIN CUSTOMER_BILLING C_BILL ON C_INFO.custId = C_BILL.custId INNER JOIN CUSTOMER_PLAN_MAP C_PLAN ON C_BILL.custId = C_PLAN.custId INNER JOIN PLAN_DETAILS P_DET ON C_PLAN.planId = P_DET.planId WHERE C_INFO.custPrimaryMtn = '"+mtnnum+"'";
				//System.out.println(sql);
				//st.setString(1, mtnnum);

				openDBConnection();
				st = conn.prepareStatement(sql);
			    //String sql = "SELECT C_INFO.custId, C_INFO.custName, C_INFO.custZip, C_BILL.billDate, C_BILL.amount, C_BILL.paymentstatus, C_BILL.dueDate, C_PLAN.PlanId, C_PLAN.isActive, P_DET.planName, P_DET.dataBw, P_DET.sms, P_DET.planPrice  FROM CUSTOMER_INFO C_INFO INNER JOIN CUSTOMER_BILLING C_BILL ON C_INFO.custId = C_BILL.custId INNER JOIN CUSTOMER_PLAN_MAP C_PLAN ON C_BILL.custId = C_PLAN.custId INNER JOIN PLAN_DETAILS P_DET ON C_PLAN.planId = P_DET.planId WHERE C_INFO.custPrimaryMtn IN ('7871234567','7871234568')";
			    //ResultSet rs = st.executeQuery(sql);
			    rs = st.executeQuery();
			    
			    while(rs.next()){   	
			    	Map map = new HashMap();
			    	map.put(rs.getMetaData().getColumnName(1), rs.getInt("custid"));
			    	map.put(rs.getMetaData().getColumnName(2), rs.getString("custName"));
			    	map.put(rs.getMetaData().getColumnName(3), rs.getDouble("amount"));
			    	map.put(rs.getMetaData().getColumnName(4), rs.getInt("planId"));
			    	map.put(rs.getMetaData().getColumnName(5), rs.getString("isActive"));
			    	map.put(rs.getMetaData().getColumnName(6), rs.getString("planName"));
			    	map.put(rs.getMetaData().getColumnName(7), rs.getInt("dataBw"));
			    	map.put(rs.getMetaData().getColumnName(8), rs.getInt("sms"));
			    	map.put(rs.getMetaData().getColumnName(9), rs.getDouble("planPrice"));
			    	map.put(rs.getMetaData().getColumnName(10), rs.getDouble("voiceMin"));
			    	
			    	//System.out.println("Total number of Key value pairs added to map is: "+map.size());
			    	
			    	Iterator entries = map.entrySet().iterator();
			    	while(entries.hasNext()){
			    		Map.Entry entry= (Map.Entry)entries.next();
			    		String key = entry.getKey().toString();
			    		String value = entry.getValue().toString();
			    		
			    		boolean result = template.contains("<"+key+">");
			    		
			    		if(result == true){
			    			//System.out.println("Search for "+key+" is "+result);
			    			template = template.replace("<"+key+">", value);
			    		}	
			    		////System.out.println("Key = "+key+", Value = "+value);
			    	}
			    	//System.out.println();
			    }
			    
			    rs.close();
			}catch(SQLException se){
		         se.printStackTrace();
			} finally{
			try{
					if(st!=null)
						conn.close();
				}catch(SQLException se){
		      }
			try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return template;
	}


//	public static void main(String[] args) {
//		try
//		{
//			fis = new FileInputStream("db.properties");
//            props.load(fis);
//            
//          //Registering MYSQL JDBC driver
//			
//			
//			openDBConnection();
//			//runQuery("7871234568","Your data plan details are Plan ID:<planId>, Plan Name: <planName>, Data Band-width: <dataBw>"); //500
//			runQuery("7871234567","Your data plan details are: <dataBw>");
//			//7871234567 - 500
//			//7871234568 - 100
//		    
//			rs.close();
//		}
//		catch(IOException ioe){
//			ioe.printStackTrace();
//		}
//		catch(ClassNotFoundException cnfe){	
//			cnfe.printStackTrace();
//		}
//		catch(SQLException sqe){	
//			sqe.printStackTrace();
//		}
//		finally{
//			try{
//					if(st!=null)
//						conn.close();
//				}catch(SQLException se){
//		      }
//			try{
//					if(conn!=null)
//						conn.close();
//				}catch(SQLException se){
//		         se.printStackTrace();
//		      }
//		   }//end of finally block for closing all other resources		   	
//	}
}