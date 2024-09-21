package com.sushma;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {


	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
	 
		int id = Integer.parseInt( req.getParameter( "id" ) );
		int salary = Integer.parseInt( req.getParameter( "sal" ) );
		
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver" );

			Connection con = DriverManager.
					getConnection("jdbc:mysql://localhost:3306/university?useSSL=false","root","Sushu@4997");

			PreparedStatement ps = con.prepareStatement( "update employee set salary=? where id=?" );
			ps.setInt( 1, salary );
			ps.setInt( 2, id );
			
			ps.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		res.setContentType( "text/html" );
		PrintWriter pw = res.getWriter();
		pw.write( "<h3 style=color:'green'>Salary Updated successfull</h3>" );
		pw.close();
	}


}
