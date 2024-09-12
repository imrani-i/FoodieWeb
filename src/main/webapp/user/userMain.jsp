<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<style>

.login {
	width: 30%; /* 로그인 요소의 너비 설정 */
	padding: 12px;
	margin: 8px auto; /* 수평 가운데 정렬을 위해 자동 마진 설정 */
	box-sizing: border-box;
	border-radius: 20px;
	border: 1px solid gray; /* 간단한 테두리 스타일 적용 */

}


.userbtn {
	font-color : #747474;
	display: inline-block;
	font-size: 15px;
	width: 130px;
	margin-top: 20px;
	margin-right: 5px;
	background-color: #FFFF8F;
	border-radius: 12px;
	padding: 10px;
	border: none;
	text-align: center;
	transition: all 0.5s;
	cursor: pointer;
}

.userbtn span {
	cursor: pointer;
	display: inline-block;
	position: relative;
	transition: 0.5s;

}

.userbtn span:after {
	content: '\00bb';
	position: absolute;
	opacity: 0;
	top: 0;
	right: -20px;
	transition: 0.5s;
}

.userbtn:hover span {
	padding-right: 25px;
}

.userbtn:hover span:after {
	opacity: 1;
	right: 0;
}


h1 {
	font-size: 70px;
}

.all_view {
	background-color : #FFFFF6;	
	margin: 0 auto; 
	align-items: center; 
	height: 100vh;
	overflow: hidden;
	text-align: center;
	width: 100%; 
	height: 100vh; 
	
}

.all_view form {
	margin-top: 10%; /* 폼 요소들의 상단 마진 추가 */
}

html, body {
	height: 100%; /* body와 html 요소의 높이를 100%로 설정 */
	margin: 0; /* body의 기본 마진 제거 */
}

</style>
<body>
	<div class="all_view">
	
	<form method = "post">
	<h1>Foodie</h1>

	<input type="text" id="username" name="username" class="login" placeholder="아이디">
	<br>
	<input type="password" id="password" name="password" class="login" placeholder="비밀번호">
	<br>
	<button type="submit" name="action" value="login" class="userbtn"  formaction="userLogin.do" style="vertical-align:middle"><span>로그인</span></button>
	<button type="submit" name="action" value="join" class="userbtn"   formaction="userJoin.do" style="vertical-align:middle"><span>회원가입</span></button>
	</form>
	</div>


</body>
</html>