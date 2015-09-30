package com.vzw.vzhackers.textfreely.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BillingDetailsModular extends DBConnection{
		
	static FileInputStream fis = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;
	
	public String inputMTNNumber()
	{
		//System.out.println("Enter MTN Number");
		Scanner scan = new Scanner(System.in);
		String mtnnum = scan.nextLine();
		
		return mtnnum;		
	}
	
	//method to execute the Billing Query and list out to the console
	public static void runQuery()
	{
		//System.out.println("Enter MTN Number");
		Scanner scan = new Scanner(System.in);
		String mtnnum = scan.nextLine();
		////System.out.println("You entered string "+mtnnum);
		
		try{
				//Executing a query
			    //st = conn.createStatement();
				String sql = "SELECT C_INFO.custId, C_INFO.custName, C_INFO.custZip, C_BILL.billDate, C_BILL.amount, C_BILL.paymentstatus, C_BILL.dueDate, C_PLAN.PlanId, C_PLAN.isActive, P_DET.planName, P_DET.dataBw, P_DET.sms, P_DET.planPrice  FROM CUSTOMER_INFO C_INFO INNER JOIN CUSTOMER_BILLING C_BILL ON C_INFO.custId = C_BILL.custId INNER JOIN CUSTOMER_PLAN_MAP C_PLAN ON C_BILL.custId = C_PLAN.custId INNER JOIN PLAN_DETAILS P_DET ON C_PLAN.planId = P_DET.planId WHERE C_INFO.custPrimaryMtn = '"+mtnnum+"'";
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
			    System.out.print(rs.getMetaData().getColumnName(7)+"\t\t");
			    System.out.print(rs.getMetaData().getColumnName(8)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(9)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(10)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(11)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(12)+"\t");
			    System.out.print(rs.getMetaData().getColumnName(13)+"\t");
			    System.out.print("\n");
			    
			    while(rs.next()){
			         int cust_Id  = rs.getInt("custid");
			         String cust_Name = rs.getString("custName");
			         String cust_Zip = rs.getString("custZip");
			         Date bill_Date = rs.getDate("billDate");
			         double cust_amount = rs.getDouble("amount");
			         String cust_payment_status = rs.getString("paymentstatus");
			         Date cust_due_Date = rs.getDate("dueDate");
			         int cust_Plan_Id = rs.getInt("PlanId");
			         String cust_is_Active = rs.getString("isActive");
			         String cust_plan_Name = rs.getString("planName");
			         int cust_data_Bw = rs.getInt("dataBw");
			         int cust_sms = rs.getInt("sms");
			         Double cust_plan_Price = rs.getDouble("planPrice");
			         
			         System.out.print(cust_Id+"\t");
			         System.out.print(cust_Name+"\t");
			         System.out.print(cust_Zip +"\t");
			         System.out.print(bill_Date+"\t");
			         System.out.print(cust_amount+"\t");
			         System.out.print(cust_payment_status+"\t\t");
			         System.out.print(cust_due_Date+"\t");
			         System.out.print(cust_Plan_Id+"\t");
			         System.out.print(cust_is_Active+"\t\t");
			         System.out.print(cust_plan_Name+"\t\t");
			         System.out.print(cust_data_Bw+"\t");
			         System.out.print(cust_sms+"\t");
			         System.out.print(cust_plan_Price+"\t");
			         //System.out.print();
			         //System.out.print();
			         System.out.print("\n");
			    }
			}catch(SQLException se){
		         se.printStackTrace();
			}
	}
	
//	public static void main(String[] args){
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
//			runQuery();
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