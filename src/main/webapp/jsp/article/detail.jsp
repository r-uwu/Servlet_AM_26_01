<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.Map"%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
Map<String, Object> article = (Map<String, Object>) request.getAttribute("article");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
</head>
<body>
    <h1>게시글 상세 페이지</h1>

    <% if(article != null) { %>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>ID</th>
                <td><%= article.get("id") %></td>
            </tr>
            <tr>
                <th>제목</th>
                <td><%= article.get("title") %></td>
            </tr>
            <tr>
                <th>작성일</th>
				<%
				LocalDateTime regDate = (LocalDateTime) article.get("regDate");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
				%>
				<td><%= regDate.format(formatter) %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><%= article.get("body") %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= article.get("writer") %></td>
            </tr>
        </table>
    <% } else { %>
        <p>게시글이 존재하지 않습니다.</p>
    <% } %>
	<form id="deleteForm" action="/Servlet_AM_26_01/article/delete" method="post" style="display:inline;">
	    <input type="hidden" name="id" value="<%= article.get("id") %>">
	    <button type="button" onclick="confirmDelete()">게시글 삭제</button>
	<script>
	function confirmDelete() {
	    if(confirm("정말 삭제하시겠습니까?")) {
	        document.getElementById("deleteForm").submit();
	    }
	}
	</script>

    <p><a href="/Servlet_AM_26_01/article/delete?id=<%=article.get("id")%>">게시글 삭제</a></p>
    <p><a href="/Servlet_AM_26_01/article/modify?id=<%=article.get("id")%>">게시글 수정</a></p>
    <p><a href="/Servlet_AM_26_01/article/list">목록으로 돌아가기</a></p>
</body>
</html>