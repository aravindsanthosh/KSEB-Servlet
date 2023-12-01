package com.kseb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/work")
public class WorkAllocation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		GetConnection gc = new GetConnection();
		try {
			Connection conn = gc.getConnection();
			pstmt = conn.prepareStatement("select * from work");
			rs = pstmt.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();

			while (rs.next()) {
				out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getInt(2) + "</td><td>" + rs.getInt(3)
						+ "</td><td>" + rs.getString(4) + "</td><td>" + rs.getDate(5) + "</td><td>" + rs.getString(6)
						+ "<td></td>" + "<form action='work' method='post'><input type='hidden' value='" + rs.getInt(1) + "'></input><button type='submit'>Allocate</button></form>" + "</td></tr>");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		GetConnection gc = new GetConnection();
		try {
			Connection conn = gc.getConnection();
			java.util.Date javaDate = new java.util.Date();
			java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
			System.out.println(req.toString());
			String cNo = req.getParameter("consumer-number");
			String pNo = req.getParameter("post-number");
			String details = req.getParameter("details");
			System.out.println(cNo + " " + pNo + " " + details);
			pstmt = conn.prepareStatement(
					"insert into `work` (`complaint_id`, `lineman_id`, `status_update_date`, `details`) values(?,?,?,?)");
			pstmt.setString(1, cNo);
			pstmt.setString(2, pNo);
			pstmt.setString(4, details);
			pstmt.setDate(3, mySQLDate);
			boolean result = pstmt.execute();
			System.out.println(result);
			System.out.println("successfully inserted");
			System.out.println(cNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
