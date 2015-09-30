package com.vzw.vzhackers.textfreely.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OfferDetailsModular extends DBConnection{
	
	static FileInputStream fis = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;

	//method to execute the Billing Query and list out to the console
	public static void runQuery()
	{		
		System.out.println("Enter Customer's MTN Number to show the Offer details");
		Scanner scan = new Scanner(System.in);
		String mtnnum = scan.nextLine();
		//System.out.println("You entered string "+mtnnum);
		
		try{
				//Executing a query
			    //st = conn.createStatement();
				String sql = "SELECT C_INFO.custId, C_INFO.custName, C_PLAN.PlanId, P_DET.planName, C_PLAN.isActive, OFF_DET.OfferId, OFF_DET.OfferPrice, OFF_DET.description, OFF_DET.startDate, OFF_DET.endDate FROM CUSTOMER_INFO C_INFO INNER JOIN CUSTOMER_BILLING C_BILL ON C_INFO.custId = C_BILL.custId INNER JOIN CUSTOMER_PLAN_MAP C_PLAN ON C_BILL.custId = C_PLAN.custId INNER JOIN PLAN_DETAILS P_DET ON C_PLAN.planId = P_DET.planId LEFT OUTER JOIN OFFER_CUSTOMER_MAP OFF_CUST ON C_BILL.custId = OFF_CUST.custId LEFT OUTER JOIN OFFER_DETAILS OFF_DET ON OFF_CUST.offerId = OFF_DET.offerId WHERE C_INFO.custPrimaryMtn = '"+mtnnum+"'";
				//st.setString(1, mtnnum);

				st = conn.prepareStatement(sql);
			    //String sql = "SELECT C_INFO.custId, C_INFO.custName, C_INFO.custZip, C_BILL.billDate, C_BILL.amount, C_BILL.paymentstatus, C_BILL.dueDate, C_PLAN.PlanId, C_PLAN.isActive, P_DET.planName, P_DET.dataBw, P_DET.sms, P_DET.planPrice  FROM CUSTOMER_INFO C_INFO INNER JOIN CUSTOMER_BILLING C_BILL ON C_INFO.custId = C_BILL.custId INNER JOIN CUSTOMER_PLAN_MAP C_PLAN ON C_BILL.custId = C_PLAN.custId INNER JOIN PLAN_DETAILS P_DET ON C_PLAN.planId = P_DET.planId WHERE C_INFO.custPrimaryMtn IN ('7871234567','7871234568')";
			    //ResultSet rs = st.executeQuery(sql);
			    rs = st.executeQuery();
			    		    
			    System.out.print(rs.getMetaData().getColumnName(1)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(2)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(3)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(4)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(5)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(6)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(7)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(8)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(9)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(10)+"\t");
			    System.out.print("\n");
			    
			    while(rs.next()){
			         int cust_Id  = rs.getInt("custid");
			         String cust_Name = rs.getString("custName");			         
			         int cust_Plan_Id = rs.getInt("PlanId");
			         String cust_plan_Name = rs.getString("planName");
			         String cust_is_Active = rs.getString("isActive");
			         int cust_Offer_Id = rs.getInt("OfferId");
			         Double cust_Offer_Price = rs.getDouble("OfferPrice");
			         String cust_offer_description = rs.getString("description");
			         Date cust_offer_startDate = rs.getDate("startDate");
			         Date cust_offer_endDate = rs.getDate("endDate");
			         
			         System.out.print(cust_Id+"\t");
			         System.out.print(cust_Name+"\t");			         
			         System.out.print(cust_Plan_Id+"\t");
			         System.out.print(cust_plan_Name+"\t\t");
			         System.out.print(cust_is_Active+"\t\t");			         
			         System.out.print(cust_Offer_Id+"\t");
			         System.out.print(cust_Offer_Price+"\t\t");
			         System.out.print(cust_offer_description+"\t\t");
			         System.out.print(cust_offer_startDate+"\t\t");
			         System.out.print(cust_offer_endDate+"\t");
			         
			         System.out.print("\n");
			         
			         System.out.println("Dear Customer, you are eligible for the offer amount of $"+cust_Offer_Price+" use before "+cust_offer_endDate+" when you are paying next bill");
			    }
			}catch(SQLException se){
		         se.printStackTrace();
			}
	}


	public static void main(String[] args) {
		try
		{
			fis = new FileInputStream("db.properties");
            props.load(fis);
            
          //Registering MYSQL JDBC driver
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));		
			System.out.println("Connecting to database...");
			
			openDBConnection();
			runQuery();
		
		    rs.close();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		catch(ClassNotFoundException cnfe){	
			cnfe.printStackTrace();
		}
		catch(SQLException sqe){	
			sqe.printStackTrace();
		}
		finally{
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
	}

}