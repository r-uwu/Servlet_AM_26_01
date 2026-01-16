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
        width: 100px;
        text-align: right;
        margin-right: 10px;
    }

    input {
        width: 180px;
    }
    </style>
</head>
<h1>회원가입</h1>
<script type="text/javascript">
function JoinForm__submit(form){
	console.log('form.userId.value : '+form.userId.value);
	if(form.userId.value.length == 0){
		alert('아이디 입력해주세요');
		return;
	}
}
}</script>
<body>
<form id="joinForm" action="/Servlet_AM_26_01/member/join" method="post">
    <div><label>아이디:</label><input autocomplete="off" placeholder="id입력" type="text" name="id" required></div>
    <div><label>비밀번호:</label><input autocomplete="off" placeholder="비밀번호 입력" type="password" name="pw" required minlength="6"></div>
    <div><label>비밀번호 확인:</label><input autocomplete="off" placeholder="비밀번호 확인" type="password" name="pwConfirm" required minlength="6"></div>
    <div><label>이름:</label><input autocomplete="off" placeholder="이름 입력" type="text" name="name" required></div>
    <br>
    <button type="submit">회원가입</button>
</form>

<script>
const form = document.getElementById('joinForm');

form.addEventListener('submit', function(e) {
    const pw = form.pw.value;
    const pwConfirm = form.pwConfirm.value;

    if (pw !== pwConfirm) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        e.preventDefault(); // 폼 제출 막기
    }
    else alert("회원 가입 완료!");
});
</script>
</body>
</html>