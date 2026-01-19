<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");
	String loginedMemberName = null;
	if (loginedMemberId != null) {
	    Object nameObj = session.getAttribute("loginedMembeName");
	    if (nameObj != null) {
	        loginedMemberName = nameObj.toString();
	    }
	}
    boolean isLogined = loginedMemberId != null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home/main</title>
</head>

<body>
<h1>메인임</h1>
    <% if (!isLogined) { %>
    <label>로그인 후 이용해주세요</label>
    <% } else { %>
	<label>회원 정보 : <%=loginedMemberName%>님</label><br>
    <% } %>
    
    
    <div><a href="/Servlet_AM_26_01/article/doWrite">게시글 작성</a></div>
    <div><a href="/Servlet_AM_26_01/article/list">게시글 목록 보기</a></div>
    
    <br>
    <% if (!isLogined) { %>
    <div><a href="/Servlet_AM_26_01/member/login">로그인</a></div>
    <% } else { %>
    <div><a href="/Servlet_AM_26_01/member/doLogout">로그아웃</a></div>
    <% } %>
	<div><a href="/Servlet_AM_26_01/member/join">회원가입</a></div>
</body>
</html>