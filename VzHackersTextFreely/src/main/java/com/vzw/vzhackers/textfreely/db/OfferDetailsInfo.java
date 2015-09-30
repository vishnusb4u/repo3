package com.vzw.vzhackers.textfreely.db;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OfferDetailsInfo extends DBConnection{
	
	static FileInputStream fis = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;

	//method to execute the Billing Query and list out to the console
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String runQuery(String mtnnum, String template)
	{	
		try{
				//Executing a query
			    //st = conn.createStatement();
				String sql = "SELECT C_INFO.custId, C_INFO.custName, C_PLAN.PlanId, P_DET.planName, C_PLAN.isActive, OFF_DET.OfferId, OFF_DET.OfferPrice, OFF_DET.description, OFF_DET.startDate, OFF_DET.endDate FROM CUSTOMER_INFO C_INFO INNER JOIN CUSTOMER_BILLING C_BILL ON C_INFO.custId = C_BILL.custId INNER JOIN CUSTOMER_PLAN_MAP C_PLAN ON C_BILL.custId = C_PLAN.custId INNER JOIN PLAN_DETAILS P_DET ON C_PLAN.planId = P_DET.planId LEFT OUTER JOIN OFFER_CUSTOMER_MAP OFF_CUST ON C_BILL.custId = OFF_CUST.custId LEFT OUTER JOIN OFFER_DETAILS OFF_DET ON OFF_CUST.offerId = OFF_DET.offerId WHERE C_INFO.custPrimaryMtn = '"+mtnnum+"'";
				//System.out.println(sql);
				//st.setString(1, mtnnum);

				openDBConnection();
				st = conn.prepareStatement(sql);
			    //String sql = "SELECT C_INFO.custId, C_INFO.custName, C_INFO.custZip, C_BILL.billDate, C_BILL.amount, C_BILL.paymentstatus, C_BILL.dueDate, C_PLAN.PlanId, C_PLAN.isActive, P_DET.planName, P_DET.dataBw, P_DET.sms, P_DET.planPrice  FROM CUSTOMER_INFO C_INFO INNER JOIN CUSTOMER_BILLING C_BILL ON C_INFO.custId = C_BILL.custId INNER JOIN CUSTOMER_PLAN_MAP C_PLAN ON C_BILL.custId = C_PLAN.custId INNER JOIN PLAN_DETAILS P_DET ON C_PLAN.planId = P_DET.planId WHERE C_INFO.custPrimaryMtn IN ('7871234567','7871234568')";
			    //ResultSet rs = st.executeQuery(sql);
			    rs = st.executeQuery();
			    
			    while(rs.next()){
			    	//System.out.println("hasNext!!!");
			         /*int cust_Id  = rs.getInt("custid");
			         String cust_Name = rs.getString("custName");			         
			         int cust_Plan_Id = rs.getInt("PlanId");
			         String cust_plan_Name = rs.getString("planName");
			         String cust_is_Active = rs.getString("isActive");
			         int cust_Offer_Id = rs.getInt("OfferId");
			         Double cust_Offer_Price = rs.getDouble("OfferPrice");
			         String cust_offer_description = rs.getString("description");
			         Date cust_offer_startDate = rs.getDate("startDate");
			         Date cust_offer_endDate = rs.getDate("endDate");*/
			         
			         /*System.out.print(cust_Id+"\t");
			         System.out.print(cust_Name+"\t");			         
			         System.out.print(cust_Plan_Id+"\t");
			         System.out.print(cust_plan_Name+"\t\t");
			         System.out.print(cust_is_Active+"\t\t");			         
			         System.out.print(cust_Offer_Id+"\t");
			         System.out.print(cust_Offer_Price+"\t\t");
			         System.out.print(cust_offer_description+"\t\t");
			         System.out.print(cust_offer_startDate+"\t\t");
			         System.out.print(cust_offer_endDate+"\t");*/
			    	Map map = new HashMap();
			    	map.put(rs.getMetaData().getColumnName(1), rs.getInt("custid"));
			    	map.put(rs.getMetaData().getColumnName(2), rs.getString("custName"));
			    	map.put(rs.getMetaData().getColumnName(3), rs.getInt("PlanId"));
			    	map.put(rs.getMetaData().getColumnName(4), rs.getString("planName"));
			    	map.put(rs.getMetaData().getColumnName(5), rs.getString("isActive"));
			    	map.put(rs.getMetaData().getColumnName(6), rs.getInt("OfferId"));
			    	map.put(rs.getMetaData().getColumnName(7), rs.getDouble("OfferPrice"));
			    	map.put(rs.getMetaData().getColumnName(8), rs.getString("description"));
			    	map.put(rs.getMetaData().getColumnName(9), rs.getDate("startDate"));
			    	map.put(rs.getMetaData().getColumnName(10), rs.getDate("endDate"));
			    	
			    	//System.out.println("Total key-value pairs added to Map are:"+map.size());
			    	
			    	Iterator entries = map.entrySet().iterator();
			    	while(entries.hasNext()){
			    		Map.Entry entry = (Map.Entry)entries.next();
			    		String key = entry.getKey().toString();
			    		//String value = (String)entry.getValue(); //Getting NullPointerException and ClassCastException If i use String variable
			    		Object value = (Object)entry.getValue();
			    		////System.out.println("Key "+key+ ", "+"Value "+value);
			    		
			    		boolean result = template.contains("<"+key+">");
			    		if(result == true){
			    			////System.out.println("Search for "+key+" is "+result);
			    			template = template.replace("<"+key+">", value.toString());
			    		}
			    	}
			    	
			    	System.out.print("\n");
			    	
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
		   }//end of finally block for closing all other resources
		return template;
	}

//	public static void main(String args[]) {
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
//			runQuery("7871234568" , "Your are eligible for <offerPrice> offer");
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
//		catch(NullPointerException npe){
//			npe.printStackTrace();
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