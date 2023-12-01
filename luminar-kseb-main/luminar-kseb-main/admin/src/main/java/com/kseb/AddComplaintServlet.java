package com.kseb;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addComplaint")
public class AddComplaintServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		
		DBConnector db = new DBConnector();
		Connection conn = null;
		conn = db.getConnection();
		PreparedStatement pst = null;
		
		try {
			LocalDateTime dateAndTime = LocalDateTime.now();
			String consumerName = req.getParameter("consumer_name");
			String consumerNumber = req.getParameter("consumer_number");
			String consumerPhn = req.getParameter("phone_number");
			String location = req.getParameter("location");
			int postNumber = Integer.parseInt(req.getParameter("post_no"));
			String complaintDescription = req.getParameter("complaint");
			Timestamp timestamp = Timestamp.valueOf(dateAndTime);
			
			pst = conn.prepareStatement("INSERT into complaint_table(consumer_name,consumer_number,consumer_phn,location,post_number,com_description,com_reg_date) values(?,?,?,?,?,?,?)");
			pst.setString(1, consumerName);
			pst.setString(2, consumerNumber);
			pst.setString(3,consumerPhn);
			pst.setString(4, location);
			pst.setInt(5, postNumber);
			pst.setString(6, complaintDescription);
			pst.setTimestamp(7, timestamp);
			pst.executeUpdate();
			res.sendRedirect("adminHome.html");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
