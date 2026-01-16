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

@WebServlet("/member/join")
public class MemberJoinServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");  // 강제 로딩
		    System.out.println("(m join)doPost 내에서 MySQL Driver 로딩 성공");
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}


        String inputId = request.getParameter("id");
        String inputPw = request.getParameter("pw");
        String inputName = request.getParameter("name");
        
        if (inputId == null || inputId.trim().isEmpty()) {
            response.getWriter().append("아이디를 입력해주세요.");
            return;
        }

        if (inputPw == null || inputPw.trim().isEmpty()) {
            response.getWriter().append("비밀번호를 입력해주세요.");
            return;
        }

        if (inputName == null || inputName.trim().isEmpty()) {
            response.getWriter().append("이름을 입력해주세요.");
            return;
        }
			
        Connection conn = null;
	
		 try {
	            String url = "jdbc:mysql://127.0.0.1:3306/Servlet_AM_26_01?serverTimezone=Asia/Seoul";
	            String user = "root";
	            String password = "";

	            conn = DriverManager.getConnection(url, user, password);

	            DBUtil dbUtil = new DBUtil(request, response);
	            
	            String pw = request.getParameter("pw");
	            String pwConfirm = request.getParameter("pwConfirm");

	            if (!pw.equals(pwConfirm)) {
	                response.getWriter().append("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
	                return;
	            }

				SecSql sql = SecSql.from("INSERT INTO member (userId, userPw, userName) VALUES");
				sql.append("(?,?,?)", inputId, inputPw, inputName);
				int id = dbUtil.insert(conn, sql);


//	            request.setAttribute("article", article);
//	            request.getRequestDispatcher("/jsp/article/join.jsp").forward(request, response);
	            response.getWriter().append("회원가입 완료.");
	            response.sendRedirect("/Servlet_AM_26_01/home/main");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try { if (conn != null && !conn.isClosed()) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	 
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");  // 강제 로딩
		    System.out.println("(m join)doGet 내에서 MySQL Driver 로딩 성공");
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} 
        request.getRequestDispatcher("/jsp/member/join.jsp")
        .forward(request, response);
    }

}
