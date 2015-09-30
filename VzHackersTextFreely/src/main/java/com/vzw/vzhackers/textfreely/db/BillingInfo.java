package com.vzw.vzhackers.textfreely.db;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BillingInfo extends DBConnection{
		
	static FileInputStream fis = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	static String mtnnum;
	
	//method to execute the Billing Query and list out to the console
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String runQuery(String mtnnum,String template)
	{	
		try{
				//Executing a query
			    //st = conn.createStatement();
				String sql = "SELECT C_INFO.custId, C_INFO.custName, C_INFO.custZip, C_BILL.billDate, C_BILL.amount, C_BILL.paymentstatus, C_BILL.dueDate, C_PLAN.PlanId, C_PLAN.isActive, P_DET.planName, P_DET.dataBw, P_DET.sms, P_DET.planPrice  FROM CUSTOMER_INFO C_INFO INNER JOIN CUSTOMER_BILLING C_BILL ON C_INFO.custId = C_BILL.custId INNER JOIN CUSTOMER_PLAN_MAP C_PLAN ON C_BILL.custId = C_PLAN.custId INNER JOIN PLAN_DETAILS P_DET ON C_PLAN.planId = P_DET.planId WHERE C_INFO.custPrimaryMtn = '"+mtnnum+"'";
				//st.setString(1, mtnnum);
				openDBConnection();

				st = conn.prepareStatement(sql);
			    //ResultSet rs = st.executeQuery(sql);
			    rs = st.executeQuery();
			    
			    while(rs.next()){
			         //Map<String, String> map = new HashMap<String, String>();
			         Map map = new HashMap();
					 map.put(rs.getMetaData().getColumnName(1), rs.getInt("custid"));
					 map.put(rs.getMetaData().getColumnName(2), rs.getString("custName"));
					 map.put(rs.getMetaData().getColumnName(3), rs.getString("custZip"));
					 map.put(rs.getMetaData().getColumnName(4), rs.getDate("billDate"));
					 map.put(rs.getMetaData().getColumnName(5), rs.getDouble("amount"));
					 map.put(rs.getMetaData().getColumnName(6), rs.getString("paymentstatus"));
					 map.put(rs.getMetaData().getColumnName(7), rs.getDate("dueDate"));
					 map.put(rs.getMetaData().getColumnName(8), rs.getInt("PlanId"));
					 map.put(rs.getMetaData().getColumnName(9), rs.getString("isActive"));
					 map.put(rs.getMetaData().getColumnName(10), rs.getString("planName"));
					 map.put(rs.getMetaData().getColumnName(11), rs.getInt("dataBw"));
					 map.put(rs.getMetaData().getColumnName(12), rs.getInt("sms"));
					 map.put(rs.getMetaData().getColumnName(13), rs.getDouble("planPrice"));
					 
					 //String cust_due_Date = rs.getString("dueDate");
					 
					 //System.out.println("Total key value pairs added to Map is: " + map.size());
					 
					 //template = "Your Billing dueDate is: <dueDate>";
					 
					 Iterator entries = map.entrySet().iterator();
					 
					 //Method 1:
					 while (entries.hasNext()) {
					     Map.Entry entry = (Map.Entry) entries.next();
					     String key = entry.getKey().toString();
					     //Object value = (Object)entry.getValue();
					     String value = entry.getValue().toString();
					     ////System.out.println("Key = " +key +", Value = " +value);
					     
					     boolean result = template.contains("<"+key+">"); //contains(key);
					     if(result == true){
					    	 ////System.out.println(template.replace("<dueDate>",cust_due_Date));
					    	template = template.replace("<"+key+">",value);
					     }
					 }
					 //System.out.println();
					 
					 //Method 2:
					 /*for(Object key: map.keySet())
					 //System.out.println("Key = "+key + " , Value = " + map.get(key));*/
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
		   }//end
		return template;
	}
	
//	public static void main(String args[]){
//		
//		try
//		{
//			fis = new FileInputStream("db.properties");
//            props.load(fis);
//            
//          //Registering MYSQL JDBC driver
//			Class.forName(props.getProperty("DB_DRIVER_CLASS"));		
//			//System.out.println("Connecting to database...");
//			
//			openDBConnection();
//			runQuery("7871234567","Your Billing Due-Date is: <dueDate>"); //billDate //planName
//		
//		    rs.close();
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