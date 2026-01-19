package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/doModify")
public class ArticleDoModifyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/Servlet_AM_26_01?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			response.getWriter().append("연결 성공");
			
            HttpSession session = request.getSession();
            Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");
            
            if (loginedMemberId == null) {
            	System.out.println("로그인 세션 없음");
                response.getWriter().append(
                    "<script>alert('로그인 후 이용해주세요'); location.replace('../member/login');</script>"
                );
                   return;
            }

			int id = Integer.parseInt(request.getParameter("id"));

			if(loginedMemberId == id) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");

			SecSql sql = SecSql.from("UPDATE article");
			sql.append("SET title = ?,", title);
			sql.append("`body` = ?", body);
			sql.append("WHERE id = ?;", id);

			DBUtil.update(conn, sql);

			response.getWriter().append(
					String.format("<script>alert('%d번 글이 수정되었어요.'); location.replace('detail?id=%d');</script>", id, id));

		} else {
            	System.out.println("(delete) 수정 정보 불일치");
                response.getWriter().append(
	                    "<script>alert('작성자만 수정할 수 있습니다'); location.replace('../article/list');</script>");
		}
			
			
		} catch (SQLException e) {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
    	System.out.println("게시글 수정 접근됨");
	}

}