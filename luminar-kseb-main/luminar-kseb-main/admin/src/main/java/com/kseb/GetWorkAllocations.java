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

@WebServlet("/get-work-allocations")
public class GetWorkAllocations extends HttpServlet{

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
			pst = conn.prepareStatement("SELECT * FROM work_allocation_table");
			rs = pst.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			
			while(rs.next()) {
				out.print("<tr> <td>"+ rs.getInt(1)+"</td><td>"+rs.getTimestamp(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td><td>");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
