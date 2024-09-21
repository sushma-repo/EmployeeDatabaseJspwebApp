package com.sushma;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sushma.entity.Employee;

public class SelectServlet extends HttpServlet {

	@Override
	protected void service( HttpServletRequest req, HttpServletResponse res )  {
		int id =    Integer.parseInt(  req.getParameter( "id" )) ;
		
		
		try {
			Class.forName( "com.mysql.jdbc.Driver" );

			Connection con = DriverManager.
					getConnection("jdbc:mysql://localhost:3306/university","root","Sushu@4997");

			PreparedStatement ps = con.prepareStatement( "select * from employee where id=?" );
			ps.setInt( 1, id );
			ResultSet rs = ps.executeQuery();
			res.setContentType( "text/html" );
			PrintWriter pw = res.getWriter();
			if ( rs.next() ) {
			
				int i = rs.getInt( "id" );
				String name = rs.getString( "name" );
				int sal = rs.getInt( "salary" );
				Employee e = new Employee( id, name, sal );
				req.setAttribute( "emp", e );
				//call display.jsp
				RequestDispatcher rd = req.getRequestDispatcher( "display.jsp" );
				rd.forward( req, res );
			} else {
				
				pw.write( "<h3 style=color:'green'>No record found!!!!</h3>" );
			}
			pw.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
