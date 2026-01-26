package com.KoreaIT.java.AM_jsp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

	public void showDetail() throws ServletException, IOException {
		
		
        int id = Integer.parseInt(request.getParameter("id"));
		
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		
        Map<String, Object> article = DBUtil.selectRow(conn, sql);

        request.setAttribute("article", article);
        request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
	}

	public void showWrite() throws IOException {
		
        HttpSession session = request.getSession();
        String inputTitle = request.getParameter("title");
        String inputBody = request.getParameter("body");
        Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");
    	String loginedMemberName = session.getAttribute("loginedMemberName").toString();
        if (loginedMemberId == null) {
        	System.out.println("로그인 세션 없음");
            response.getWriter().append(
                "<script>alert('로그인 후 이용해주세요'); location.replace('../member/login');</script>"
            );
               return;
        }
        else 	System.out.println("일단 로그인 세션은 있음");
        
		SecSql sql = SecSql.from("INSERT INTO article");
		sql.append("(title, body, writer, userId) VALUES (?,?,?,?)",inputTitle,inputBody,loginedMemberName, loginedMemberId);
		int id = DBUtil.insert(conn, sql);
	
		response.sendRedirect(request.getContextPath() + "/article/detail?id=" + id);
		
	}

	public void showModify() {
		// TODO Auto-generated method stub
		
	}

	public void doDelete() throws ServletException, IOException {
        String inputId = request.getParameter("id");

        if (inputId == null || inputId.isEmpty()) {
            response.sendRedirect("/Servlet_AM_26_01/article/list");
            return;
        }

        int id = Integer.parseInt(inputId);

        Connection conn = null;

            
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
	            
				SecSql sql = SecSql.from("DELETE FROM article");
				sql.append("WHERE id = ?", id);
				DBUtil.update(conn, sql);
				
	            request.getRequestDispatcher("/jsp/article/delete.jsp").forward(request, response);
            }
            else {                                                                                                                                                                                                                                             
            	System.out.println("(delete) 삭제 정보 불일치");
                response.getWriter().append(
	                    "<script>alert('작성자만 삭제할 수 있습니다'); location.replace('../article/list');</script>");
            }
		
	}
}
