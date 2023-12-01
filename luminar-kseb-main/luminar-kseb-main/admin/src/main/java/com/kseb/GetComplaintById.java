package com.kseb;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetComplaintById extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String complaintId = req.getParameter("id");
		DBConnector db = new DBConnector();
		try {
			conn = db.getConnection();
			pst = conn.prepareStatement("SELECT * FROM complaint_table where com_id=?");
			pst.setString(1, complaintId);
			rs = pst.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			
			while(rs.next()) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
