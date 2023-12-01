package com.kseb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/work-allocation")
public class WorkAllocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		try {
			res.sendRedirect("workAllocation.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
