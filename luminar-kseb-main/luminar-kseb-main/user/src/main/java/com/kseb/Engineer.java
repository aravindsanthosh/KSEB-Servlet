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

@WebServlet("/engineer")
public class Engineer extends HttpServlet {
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
			pstmt = conn.prepareStatement("select * from materials_request");
			rs = pstmt.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			while (rs.next()) {
				out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getInt(2) + "</td><td>" + rs.getInt(3)
						+ "</td><td>" + rs.getDate(4) + "</td><td>" + rs.getInt(5) + "</td><td>" + rs.getBoolean(6)
						+ "</td><td>" + rs.getBoolean(7) + "</td><td><form action='engineer' method='post'>\r\n"
						+ "<input name='material' type='hidden' value='" + rs.getInt(2) + "'>\r\n"
						+ "<button type=\"submit\">ISSUE</button></form>\r\n" + "</td></tr>");

			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		GetConnection gc = new GetConnection();
		try {
			Connection conn = gc.getConnection();
			java.util.Date javaDate = new java.util.Date();
			java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
			System.out.println(req.toString());
			String materialId = req.getParameter("material");
			System.out.print(materialId);

			pstmt = conn.prepareStatement("update materials_request set eng_approved=true where material_id=?");
			pstmt.setString(1, materialId);
			int result = pstmt.executeUpdate();
			System.out.println(result);
			System.out.println("successfully updated");
			res.sendRedirect("materials-request.html");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}