<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int articleTotal = (articleRows != null)? articleRows.size() : 0;
%>
<%
Integer cpObj = (Integer) request.getAttribute("currentPage");
Integer tpObj = (Integer) request.getAttribute("totalPages");

int currentPage = (cpObj != null) ? cpObj : 1;
int totalPage = (tpObj != null) ? tpObj : 1;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>

<style>

</style>


</head>
<body>
	<h1>게시글 목록</h1>
	
	


<% if(articleTotal != 0) {%>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>번호</th>
        <th>작성일자</th>
        <th>제목</th>
        <th>작성자</th>
    </tr>
<%        for(int i = 0; i < articleTotal; i++) { %>
    <tr>
        <td>
            <a href='/Servlet_AM_26_01/article/detail?id=<%= articleRows.get(i).get("id") %>'>
                <%= articleRows.get(i).get("id") %>번
            </a>
        </td>
        <td>
        <%
		LocalDateTime regDate = (LocalDateTime) articleRows.get(i).get("regDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 hh시 mm분");
        %>
        <%= regDate.format(formatter) %></td>
        <td style="width:300px">
            <a href='/Servlet_AM_26_01/article/detail?id=<%= articleRows.get(i).get("id") %>'>
                <%= articleRows.get(i).get("title") %>
            </a>
        </td>
        <td>
        <%= articleRows.get(i).get("writer") %>
        </td>
    </tr>
<%   } %>
</table>

<!-- 이전페이지 -->
<label>
<% if(currentPage > 1) { %>
    <a href="<%= request.getContextPath() %>/article/list?page=<%= currentPage-1 %>">이전 페이지</a>
<% } %>

</label>


<!-- 현재 페이지 기반으로 넘버링 페이지 -->

<label style="border:1px solid gray">
<%
int firstPage = currentPage - 5;
if(firstPage < 1) firstPage = 1;

for(int i = firstPage; i<10+firstPage; i++){ %>

<a
style="<%= (i==currentPage) ? "font-weight:bold" : "" %>"
href="<%= request.getContextPath() %>/article/list?page=<%=i%>"><%=i%></a>

<%
if(i == totalPage) break;
%>
	
<%}%>

</label>
<!-- 다음페이지 뒤로 옮김 -->
<label>
<% if(currentPage < totalPage) { %>
    <a href="<%= request.getContextPath() %>/article/list?page=<%= currentPage+1 %>">다음 페이지</a>
<% } %>
</label>


<%   } else { %>
        <div>게시글이 존재하지 않습니다.</div>
<% } %>

    <div><a href="/Servlet_AM_26_01/home/main">홈으로 이동하기</a></div>
    <div><a href="/Servlet_AM_26_01/article/doWrite">게시글 작성하기</a></div>

</body>
</html>