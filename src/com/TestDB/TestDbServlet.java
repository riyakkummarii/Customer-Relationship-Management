package com.TestDB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// setup connection variables
		
		String user="springstudent";
		String password="springstudent";
		
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
		
		// actual name of the jdbc driver class for mysql
		String driver="com.mysql.jdbc.Driver";
		
		//get connection to database
		
		try {
			PrintWriter out=response.getWriter();
			
			out.println("Connecting to database: "+jdbcUrl);
			
			Class.forName(driver);
			
			Connection myconn=DriverManager.getConnection(jdbcUrl, user, password);
			
			out.println("\nSuccess!!!");
			
			myconn.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		
	}

	

}
