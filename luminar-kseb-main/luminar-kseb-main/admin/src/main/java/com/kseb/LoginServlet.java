package com.kseb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DBConnector db = new DBConnector();
		
		conn = db.getConnection();

		try {
			String uname = req.getParameter("username");
			String password = req.getParameter("password");
			String userType = req.getParameter("userType");

			pst = conn.prepareStatement("SELECT * from user_details WHERE user_name=? and password=? and user_type=? ");
			pst.setString(1, uname);
			pst.setString(2, password);
			pst.setString(3, userType);
			rs = pst.executeQuery();

			String name = null;
			String pass = null;
			while (rs.next()) {
				name = rs.getString(2);
				pass = rs.getString(3);
			}

			if (userType.equals("admin")) {
				if (uname.equals(name) && password.equals(pass)) {
					HttpSession session = req.getSession();
					session.setAttribute("name", uname);

					res.sendRedirect("admin-home");

				} else {
					res.sendRedirect("index.html?error=true");
				}

			}
		} catch (SQLException | IOException e) {

			e.printStackTrace();
		}

	}
}
