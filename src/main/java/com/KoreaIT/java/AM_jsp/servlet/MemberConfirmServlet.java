package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/confirm")
public class MemberConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");  // 강제 로딩
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}

		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}
}
