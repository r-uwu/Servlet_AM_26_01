//
//package com.KoreaIT.java.AM_jsp.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import com.KoreaIT.java.AM_jsp.servlet.controller.ArticleController;
//import com.KoreaIT.java.AM_jsp.util.DBUtil;
//import com.KoreaIT.java.AM_jsp.util.SecSql;
//
//
//@WebServlet("/s/**")
//public class DispatcherServlet extends HttpServlet {
//
//       
//
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//response.setContentType("text/html;charset=UTF-8");
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("클래스 없음");
//			e.printStackTrace();
//		}
//
//		String url = "jdbc:mysql://127.0.0.1:3306/Servlet_AM_26_01?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
//		String user = "root";
//		String password = "";
//
//		Connection conn = null;
//
//		try {
//			conn = DriverManager.getConnection(url, user, password);
//
//			
//            HttpSession session = request.getSession();
//			boolean isLogined = false;
//			int loginedMemberId = -1;
//			Map<String, Object> loginedMember = null;
//			
//			if(session.getAttribute("loginedMemberId") != null)
//			{
//				
//				isLogined = true;
//				loginedMemberId = (int) session.getAttribute("loginedMemberId");
//				loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
//			}
//			
//			request.setAttribute("isLogined", isLogined);
//			request.setAttribute("loginedMemberId", loginedMemberId);
//			request.setAttribute("loginedMember", loginedMember);
//			
//			String requestUri = request.getRequestURI();
//			System.out.println(requestUri);
//			
//			String[] reqUriBits = requestUri.split("/");
//
//			if (reqUriBits.length < 5) {
//				response.getWriter().append(
//						String.format("<script>alert('올바른 요청이 x'); location.replace('../home/main');</script>"));
//				return;
//			}
//
//			String controllerName = reqUriBits[3];
//			String actionMethodName = reqUriBits[4];
//
//			if (controllerName.equals("article")) {
//				ArticleController articleController = new ArticleController(request, response, conn);
//
//				if (actionMethodName.equals("list")) {
//					articleController.showList();
//				}
//
//			
//		} catch (SQLException                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    e) {
//			System.out.println("에러 : " + e);
//		} finally {
//			try {
//				if (conn != null && !conn.isClosed()) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doGet(request, response);
//	}
//
//}
//	

package com.KoreaIT.java.AM_jsp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.controller.ArticleController;
import com.KoreaIT.java.AM_jsp.controller.HomeController;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		// DB 드라이버 로딩
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 없음");
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/Servlet_AM_26_01"
				+ "?useUnicode=true&characterEncoding=utf8"
				+ "&autoReconnect=true&serverTimezone=Asia/Seoul";

		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

			//======== 로그인 정보 공통 처리 =================
			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String, Object> loginedMember = null;

			if (session.getAttribute("loginedMemberId") != null) {
				isLogined = true;
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMember", loginedMember);

			// ================= URI 분석 =================
			String requestUri = request.getRequestURI();
			System.out.println(requestUri);

			String[] reqUriBits = requestUri.split("/");

			// /프로젝트명/s/article/list
			if (reqUriBits.length < 5) {
				response.sendRedirect("../home/main");
				return;
			}

			String controllerName = reqUriBits[3];
			String actionMethodName = reqUriBits[4];

			// ================= HomeController =================
			if (controllerName.equals("home")) {
				HomeController homeController = new HomeController(request, response);
				homeController.showMain();
				return;
			}

			// ================= ArticleController =================
			if (controllerName.equals("article")) {
				ArticleController articleController =
						new ArticleController(request, response, conn);

				switch (actionMethodName) {
					case "list":
						articleController.showList();
						break;
					case "detail":
						articleController.showDetail();
						break;
					case "write":
						articleController.showWrite();
						break;
					case "doWrite":
						articleController.showWrite();
						break;
					case "modify":
						articleController.showModify();
						break;
					case "doModify":
						articleController.showModify();
						break;
					case "doDelete":
						articleController.doDelete();
						break;
					default:
						response.sendRedirect(request.getContextPath() + "/home/main");
				}
				return;
			}

			response.sendRedirect(request.getContextPath() + "/home/main");

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
