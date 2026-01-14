<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int articleTotal = (articleRows != null)? articleRows.size() : 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<h1>게시글 목록</h1>
	<table border="1" cellpadding="8" cellspacing="0">
	<ul>

	<% 
	if(articleTotal != 0) {
	for(int i = 0; i< articleTotal; i++) { %>
			<li><a href='/article/detail?id=<%= articleRows.get(i).get("id")%>'><%=articleRows.get(i).get("id")%>번</a>, <%=articleRows.get(i).get("regDate")%>,
			<%=articleRows.get(i).get("title")%>, <%=articleRows.get(i).get("body")%></li>

			<% }}
	else {%>
	<li>게시글이 존재하지 않습니다.</li>
	<%} %>
			
	</ul>
	</table>
</body>
</html>