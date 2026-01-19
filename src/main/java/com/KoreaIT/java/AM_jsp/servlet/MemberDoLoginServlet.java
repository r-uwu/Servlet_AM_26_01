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

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");  // 강제 로딩
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

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM `member`");
			sql.append("WHERE userId = ?;", loginId);

			Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);

			System.out.println(memberRow);

			if (memberRow.isEmpty()) {
				response.getWriter().append(String
						.format("<script>alert('아이디 %s는 존재하지 않습니다'); location.replace('../member/login')</script>", loginId));
				return;
			}

			if (memberRow.get("userPw").equals(loginPw) == false) {
				response.getWriter()
						.append(String.format("<script>alert('비밀번호가 맞지 않습니다'); location.replace('../member/login')</script>"));
				return;
			}

			HttpSession session = request.getSession();
			session.setAttribute("loginedMember", memberRow);
			session.setAttribute("loginedMemberId", memberRow.get("id"));
			session.setAttribute("loginedMemberLoginId", memberRow.get("userId"));
			session.setAttribute("loginedMemberName", memberRow.get("userName"));

			response.getWriter().append(String.format(
					"<script>alert('%s님 로그인!'); location.replace('../home/main');</script>", memberRow.get("userName")));

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

}
}
