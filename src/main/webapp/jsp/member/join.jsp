<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
    <style>
    label {
        display: inline-block;
        width: 80px;
        text-align: right;
        margin-right: 10px;
    }

    input {
        width: 180px;
    }
    </style>
</head>
<h1>회원가입</h1>s
<body>
<form action="/Servlet_AM_26_01/member/join" method="post">
    <div><label>아이디:</label><input type="text" name="id" required></div>
    <div><label>비밀번호:</label><input type="password" name="pw" required minlength="6"></div>
    <div><label>이름:</label><input type="text" name="name" required></div>
    <br>
    <button type="submit">회원가입</button>
</form>
</body>
</html>