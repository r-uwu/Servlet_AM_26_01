package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/delete")
public class ArticleDeleteServlet extends HttpServlet {

	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String inputId = request.getParameter("id");

	        if (inputId == null || inputId.isEmpty()) {
	            response.sendRedirect("/Servlet_AM_26_01/article/list");
	            return;
	        }

	        int id = Integer.parseInt(inputId);

	        Connection conn = null;

	        try {
	            String url = "jdbc:mysql://127.0.0.1:3306/Servlet_AM_26_01?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
	            String user = "root";
	            String password = "";

	            conn = DriverManager.getConnection(url, user, password);

	            DBUtil dbUtil = new DBUtil(request, response);

				SecSql sql = SecSql.from("DELETE FROM article");
				sql.append("WHERE id = ?", id);
				dbUtil.update(conn, sql);
				
	            request.getRequestDispatcher("/jsp/article/delete.jsp").forward(request, response);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try { if (conn != null && !conn.isClosed()) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
}
