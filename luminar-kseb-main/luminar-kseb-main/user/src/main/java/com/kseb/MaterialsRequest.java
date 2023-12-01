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

@WebServlet("/materials-request")
public class MaterialsRequest extends HttpServlet {

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
			pstmt = conn.prepareStatement("select * from materials_request");
			rs = pstmt.executeQuery();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			while (rs.next()) {
				out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getInt(2) + "</td><td>" + rs.getInt(3)
						+ "</td><td>" + rs.getDate(4) + "</td><td>" + rs.getInt(5) + "</td><td>" + rs.getBoolean(6)
						+ "</td><td>" + rs.getBoolean(7) + "</td></tr>");

			}

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
			String id = req.getParameter("material");
			String lineman_id = req.getParameter("lineman");
			String quantity = req.getParameter("quantity");
			pstmt = conn.prepareStatement(
					"insert into `materials_request` (`material_id`, `lineman_id`, `req_date`, `quantity`, `status`, `eng_approved`) values(?,?,?,?,?,?)");
			
			pstmt.setString(1, id);
			pstmt.setString(2, lineman_id);
			pstmt.setDate(3, mySQLDate);
			pstmt.setString(4, quantity);
			pstmt.setBoolean(5, false);
			pstmt.setBoolean(6, false);

			boolean result = pstmt.execute();
			System.out.println(result);
			System.out.println("successfully inserted");
			res.sendRedirect("materials-request.html");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
