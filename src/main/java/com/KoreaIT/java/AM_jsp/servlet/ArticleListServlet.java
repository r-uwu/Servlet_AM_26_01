package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("클래스 없음");
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/Servlet_AM_26_01?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			response.getWriter().append("연결 성공");
			DBUtil dbUtil = new DBUtil(request, response);
	
			//페이지네이션
	        String inputPage = request.getParameter("page");
	        int page = 1;
	        
	        if(inputPage != null && !inputPage.isEmpty()) {
	            try {
	                page = Integer.parseInt(inputPage);
	            } catch(NumberFormatException e) {
	                page = 1;
	            }
	        }
	        
	        int itemsPerPage = 10;
	        int offset = (page-1) * itemsPerPage;
	        
			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT ?", itemsPerPage);
			sql.append("OFFSET ?", offset);
	        
	        List<Map<String, Object>> articleRows = dbUtil.selectRows(conn, sql);
	        request.setAttribute("articleRows", articleRows);
	        request.setAttribute("currentPage", page);
			
	        SecSql countSql = SecSql.from("SELECT COUNT(*) AS cnt FROM article");
            Map<String, Object> result = dbUtil.selectRow(conn, countSql);
            int totalCount = ((Number) result.get("cnt")).intValue();
            int totalPages = (int) Math.ceil((double) totalCount / itemsPerPage);
            request.setAttribute("totalPages", totalPages);
	

//			SecSql sql = SecSql.from("SELECT *");
//			sql.append("FROM article");
//			sql.append("ORDER BY id DESC");
//
//			List<Map<String, Object>> articleRows = dbUtil.selectRows(conn, sql);
//
//			request.setAttribute("articleRows", articleRows);

			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);

		} catch (SQLException                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

