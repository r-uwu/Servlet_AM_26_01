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
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/delete")
public class ArticleDeleteServlet extends HttpServlet {

	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

			response.setContentType("text/html;charset=UTF-8");
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
	            
	            HttpSession session = request.getSession();
	            Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");
	            
	            if (loginedMemberId == null) {
	            	System.out.println("로그인 세션 없음");
	                response.getWriter().append(
	                    "<script>alert('로그인 후 이용해주세요'); location.replace('../member/login');</script>"
	                );
	                   return;
	            }
	            
	            
	            if(loginedMemberId == id) {
		            
		            conn = DriverManager.getConnection(url, user, password);

		            DBUtil dbUtil = new DBUtil(request, response);

					SecSql sql = SecSql.from("DELETE FROM article");
					sql.append("WHERE id = ?", id);
					dbUtil.update(conn, sql);
					
		            request.getRequestDispatcher("/jsp/article/delete.jsp").forward(request, response);
	            }
	            else {
	            	System.out.println("(delete) 삭제 정보 불일치");
	                response.getWriter().append(
		                    "<script>alert('작성자만 삭제할 수 있습니다'); location.replace('../article/list');</script>");
	            }
	            	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try { if (conn != null && !conn.isClosed()) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String inputId = request.getParameter("id");

	        if(inputId == null || inputId.isEmpty()) {
	            response.sendRedirect("/Servlet_AM_26_01/article/list");
	            return;
	        }

	        int id;
	        try { id = Integer.parseInt(inputId); }
	        catch(NumberFormatException e) { 
	            response.sendRedirect("/Servlet_AM_26_01/article/list");
	            return; 
	        }

	        try (Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://127.0.0.1:3306/Servlet_AM_26_01?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul",
	                "root", "")) {

	            DBUtil dbUtil = new DBUtil(request, response);
				SecSql sql = SecSql.from("DELETE FROM article");
				sql.append("WHERE id = ?", id);
				dbUtil.update(conn, sql);

	            // 삭제 후 목록으로 redirect
	            response.sendRedirect("/Servlet_AM_26_01/article/list");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
