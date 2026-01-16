<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
<h1>게시글 작성</h1>
<form action="/Servlet_AM_26_01/article/doWrite" method="post">
    <div class="article-title"><label>제목</label><input type="text" name="title" required></div>
    <div class="article-body"><label>내용</label><input type="text" name="body" required></div>
    <br>
    <button type="submit">작성하기</button>
</form>
</body>
</html>