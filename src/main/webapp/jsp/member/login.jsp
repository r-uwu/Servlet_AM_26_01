<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page session = "true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <style>
    label {
        display: inline-block;
        width: 100px;
        text-align: right;
        margin-right: 10px;
    }

    input {
        width: 180px;
    }
    </style>
</head>
<body>
<h1>로그인</h1>


<script>
function LoginForm__submit(form) {

	let loginId = form.loginId.value.trim();
	let loginPw = form.loginPw.value.trim();

	if (form.loginId.value.length == 0) {
		alert('id 입력하세요');
		return;
	}
	if (loginPw.length == 0) {
		alert('pw 입력하세요');
		return;
	}

	form.submit();

}
</script>

<form onsubmit="LoginForm__submit(this); return false;"
		action="doLogin" method="POST">
<div><label>아이디 :</label><input autocomplete="off" placeholder="id입력" type="text" name="loginId" required></div>
<div><label>비밀번호 :</label><input autocomplete="off" placeholder="비밀번호 입력" type="password" name="loginPw" required minlength="6"></div>
<br>
<button type="submit">로그인</button>
</form>


</body>
</html>