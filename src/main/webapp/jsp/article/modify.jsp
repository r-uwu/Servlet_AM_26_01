<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>


<style>
label {
margin: 10px;
}
.article-title > input {
width: 400px;
}
.article-body > input {
width:400px;
height:300px;
}
</style>

</head>
<body>
	<h1><%=articleRow.get("id")%>번 게시글 수정</h1>
	
	<form action="doModify" method="post">
	<div><input name="id" type="hidden" value="<%=articleRow.get("id")%>" /></div>
    <div class="article-title"><label>새 제목</label><input type="text" name="title" required placeholder="새 제목"
				value="<%=articleRow.get("title")%>"></div>
    <div class="article-body"><label>새 내용</label><input type="text" name="body" required placeholder="새 내용" value="<%=articleRow.get("body")%>"></div>
    <br>
    <button type="submit" value="수정">수정하기</button>
</form>


    <div><a href="/Servlet_AM_26_01/home/main">홈으로 이동하기</a></div>
</body>
</html>