package com.kseb;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get-complaints")
public class GetComplaints extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		
		DBConnector db = new DBConnector();
		
		try {
			conn = db.getConnection();
			pst = conn.prepareStatement("SELECT * FROM  complaint_table");
			rs = pst.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			
			while(rs.next()) {
				
				
				out.print("<tr> <td>"+ rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getInt(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getTimestamp(8)+"</td><td>"+rs.getString(9)+"</td><td><button class='editButton'>Edit</button></td></tr>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
