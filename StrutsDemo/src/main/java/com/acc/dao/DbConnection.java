package com.acc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnection {

	public static Connection getConnection() {
		Connection conn=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoDatabase","root","root@123");  
			return conn;
			}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
		
		return conn;
		}  
	
			
	}
