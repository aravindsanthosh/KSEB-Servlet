package com.kseb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/materials-issue")
public class MaterialsDepoIssue extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = -4154916579059634707L;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		GetConnection gc = new GetConnection();
		try {
			Connection conn = gc.getConnection();
			pstmt = conn.prepareStatement("select * from lineman_request");
			rs = pstmt.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			while (rs.next()) {
				out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getDate(2) + "</td><td>" + rs.getInt(3)
						+ "</td><td>" + rs.getInt(4) + "</td><td>" + rs.getBoolean(5) + "</td><td>" + rs.getInt(6)
						+ "</td></tr>");
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

			pstmt = conn.prepareStatement("update `materials_request` set 'status'=true");

			int result = pstmt.executeUpdate();
			System.out.println(result);
			System.out.println("successfully updated");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}