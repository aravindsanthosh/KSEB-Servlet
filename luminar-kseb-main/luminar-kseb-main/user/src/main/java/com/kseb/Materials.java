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

@WebServlet("/materials")
public class Materials extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 800565525592758690L;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	GetConnection gc = new GetConnection();

	public void doGet(HttpServletRequest req, HttpServletResponse res) {

		try {
			Connection conn = gc.getConnection();
			pstmt = conn.prepareStatement("select * from materials");
			rs = pstmt.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
//			out.print("<html><body>");
//			out.print("<h1>Student Details</h1>");
//			out.print("<table>");
//			out.print("<tr><th>ROLL NO</th><th>NAME</th><th>MARK</th></tr>");

			while (rs.next()) {
				out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td><td>" + rs.getString(4) + "</td><td>" + rs.getInt(5) + "</td><td>" + rs.getString(6) + "</td><td>" + rs.getDate(7)
						+ "</td></tr>");
			}

//			out.print("</body></html>");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {

		try {
			Connection conn = gc.getConnection();
			java.util.Date javaDate = new java.util.Date();
			java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
			System.out.println(req.toString());
			String id = req.getParameter("id");
			String brand = req.getParameter("brand");
			String name = req.getParameter("name");
			String type = req.getParameter("type");
			String quantity = req.getParameter("quantity");
			String unit = req.getParameter("unit");
			pstmt = conn.prepareStatement(
					"insert into `materials` (`brand`, `name`, `type`, `quantity`, `unit`, `add_date`) values(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, brand);
			pstmt.setString(2, name);
			pstmt.setString(3, type);
			pstmt.setString(4, quantity);
			pstmt.setString(5, unit);
			pstmt.setDate(6, mySQLDate);
			boolean result = pstmt.execute();
			System.out.println(result);
			System.out.println("successfully inserted");
			res.sendRedirect("materials-add.html");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
