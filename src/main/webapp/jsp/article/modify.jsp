<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
	Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
	%>
	
	<%
    Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");
    
    if (loginedMemberId == null) {
    	System.out.println("로그인 세션 없음");
        response.getWriter().append(
            "<script>alert('로그인 후 이용해주세요'); location.replace('../member/login');</script>"
        );
           return;
    }
    %>
    <%
    System.out.println(articleRow);
    System.out.println("articleRow id : "+articleRow.get("userId"));
    //int articleMemberId = (Integer) articleRow.get("userId");
    
    
    int articleMemberId = 0;
    
    
    
    if (articleRow.get("userId") == null || articleRow.get("userId").equals("")) {
    	System.out.println("(modify) 수정 정보 불일치");

        response.getWriter().append(
                "<script>alert('작성자만 수정할 수 있습니다'); location.replace('../article/list');</script>");
        return;
    }
    else        articleMemberId = Integer.parseInt(articleRow.get("userId").toString());
       
    /*
    if (articleRow.get("userId") != null || !articleRow.get("userId").equals("")) {
        articleMemberId = Integer.parseInt(articleRow.get("userId").toString());
    }
    
    else {
    	System.out.println("(modify) 수정 정보 불일치");

    	
        response.getWriter().append(
                "<script>alert('작성자만 수정할 수 있습니다'); location.replace('../article/list');</script>");
	}
    */
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