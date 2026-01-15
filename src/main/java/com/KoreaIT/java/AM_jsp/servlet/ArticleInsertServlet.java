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

@WebServlet("/article/doWrite")
public class ArticleInsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        String inputTitle = request.getParameter("title");
        String inputBody = request.getParameter("body");

        if (inputTitle == null || inputTitle.isEmpty()) {
        	inputTitle = "제목 없음";

        }
        if (inputBody == null || inputBody.isEmpty()) {
        	inputBody = "내용 없음";
        }
		
        Connection conn = null;

        try {
            String url = "jdbc:mysql://127.0.0.1:3306/Servlet_AM_26_01?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            String user = "root";
            String password = "";

            conn = DriverManager.getConnection(url, user, password);

            DBUtil dbUtil = new DBUtil(request, response);

			SecSql sql = SecSql.from("INSERT INTO article");
			sql.append("(title, body) VALUES (?,?)",inputTitle,inputBody);
			int id = dbUtil.insert(conn, sql);

//            request.getRequestDispatcher("/jsp/article/doWrite.jsp").forward(request, response);
			request.setAttribute("msg", "새로운 게시글 작성이 완료되었습니다!");
			request.setAttribute("redirectUrl", request.getContextPath() + "/article/detail?id=" + id);
			request.getRequestDispatcher("/jsp/article/redirect.jsp").forward(request, response);

			response.sendRedirect(request.getContextPath() + "/article/detail?id=" + id);
			
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null && !conn.isClosed()) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
	
	}

}