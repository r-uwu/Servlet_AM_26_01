//package com.KoreaIT.java.AM_jsp.servlet.controller;
//
//import jakarta.servlet.ServletException;
//
//
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.List;
//import java.util.Map;
//
//import com.KoreaIT.java.AM_jsp.util.SecSql;
//import com.KoreaIT.java.AM_jsp.util.DBUtil;
//
//
//public class ArticleController {
//	
//	private HttpServletRequest request;
//	private HttpServletResponse response;
//	
//	private Connection conn;
//	
//	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
//		this.conn = conn;
//		this.request = request;
//		this.response = response;
//		this.conn = conn;
//	}
//       
//	private boolean isLogined() {
//		return request.getSession().getAttribute("loginedMemberId") != null;
//	}
//
//	private int getLoginedMemberId() {
//		return (int) request.getSession().getAttribute("loginedMemberId");
//	}
//		
//		public void showList() {
//			
//			  String inputPage = request.getParameter("page");
//		        int page = 1;
//		        
//		        if(inputPage != null && !inputPage.isEmpty()) {
//		            try {
//		                page = Integer.parseInt(inputPage);
//		            } catch(NumberFormatException e) {
//		                page = 1;
//		            }
//		        }
//		        
//		        int itemsPerPage = 10;
//		        int offset = (page-1) * itemsPerPage;
//		        
//				SecSql sql = SecSql.from("SELECT *");
//				sql.append("FROM article");
//				sql.append("ORDER BY id DESC");
//				sql.append("LIMIT ?", itemsPerPage);
//				sql.append("OFFSET ?", offset);
//		        
//		        List<Map<String, Object>> articleRows = dbUtil.selectRows(conn, sql);
//		        request.setAttribute("articleRows", articleRows);
//		        request.setAttribute("currentPage", page);
//				
//		        SecSql countSql = SecSql.from("SELECT COUNT(*) AS cnt FROM article");
//	            Map<String, Object> result = dbUtil.selectRow(conn, countSql);
//	            int totalCount = ((Number) result.get("cnt")).intValue();
//	            int totalPages = (int) Math.ceil((double) totalCount / itemsPerPage);
//	            request.setAttribute("totalPages", totalPages);
//		
//				request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
//				request.setAttribute("currentPage", page);
//				request.setAttribute("totalPages", totalPages);
//		}
//
//}

package com.KoreaIT.java.AM_jsp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
	}
	
	public void showList() throws ServletException, IOException {
		
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
	        
	        List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
	        request.setAttribute("articleRows", articleRows);
	        request.setAttribute("currentPage", page);
			
	        SecSql countSql = SecSql.from("SELECT COUNT(*) AS cnt FROM article");
            Map<String, Object> result = DBUtil.selectRow(conn, countSql);
            int totalCount = ((Number) result.get("cnt")).intValue();
            int totalPages = (int) Math.ceil((double) totalCount / itemsPerPage);
            request.setAttribute("totalPages", totalPages);
	
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
//			request.setAttribute("currentPage", page);
//			request.setAttribute("totalPages", totalPages);

	}
}
