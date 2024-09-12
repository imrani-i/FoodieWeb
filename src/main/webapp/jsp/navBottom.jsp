<%@page import="com.foodie.member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



<style>
.bottom_menu {
	align-items: center;
	padding-bottom:1%;
	position: fixed;
	bottom: 0px;
	width: 100%;
	height: 6%;
	z-index: 2;
	background-color: #FFFFF6;
	text-align: center;
	display: flex; /* Flexbox 사용 */
}

.bottom_menu>div {
	padding-top: 7%; 
	flex:1;
	text-align: center;
	height: 100%;
}

.bottom_menu>div img {
	height: 50%; /* 이미지 높이를 부모 요소의 높이에 맞춤 */
}

.sidenav {
	height: 100%;
	width: 0; /* 초기 너비를 0으로 설정하여 숨김 */
	position: fixed;
	z-index: 1;
	right: 0;
	top: 0;
	background-color: #FFFFF6;
	overflow-x: hidden;
	display: none;
	/* transition: width 0.3s; */
	transition: 0.5s;
}

.sidenav div {
	padding: 15px 8px 10px 16px;
	text-decoration: none;
	font-size: 15px;
	color: #818181;
}

.sidenav a:hover {
	color: #f1f1f1;
}

.sidenav.open {
	width: 15%; /* 사이드바가 열렸을 때의 너비 */
}

#username {
	margin: 20px;
}

/* 미디어 쿼리 */
@media only screen and (max-width: 390px) {
	.all_view {
		width: 100%; /* 모바일에서는 넓이가 100% */
	}
	.bottom_menu {
		width: 100%; /* 모바일에서는 넓이가 100% */
	}
	.bottom_menu>div {
		width: 33.33%; /* 모바일에서는 넓이가 33.33% */
	}
	.sidenav {
		width: 30%; /* 모바일에서는 사이드 바 너비가 50% */
		right: 0; /* 모바일에서는 오른쪽에 고정 */
	}
}

@media only screen and (min-width: 768px) {
	.all_view {
		width: 50%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		
	}
	.bottom_menu {
		width: 50%;
	}
	.bottom_menu>div {
		width: 15%; /* 데스크톱에서는 넓이가 20% */
	}
	.sidenav {
		width: 15%; /* 데스크톱에서는 사이드 바 너비가 20% */
		right: 25%; /* 데스크톱에서는 오른쪽에 25% 정렬 */
	}
}
</style>
</head>
<body>


	<div class="bottom_menu">
		<div id="postbtn">
			<img src="../image/post.png">

		</div>

		<div id="homebtn">
			<img src="../image/home.png">
		</div>

		<div id="userbtn">
			<img src="../image/user.png">
		</div>




	</div>


	<!-- 사이드바 -->
	<div id="mySidenav" class="sidenav">

		<%
		MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
		%>
		<div id="username">
			<%=loginMember.getId()%>님
		</div>
		<div>회원정보</div>
		<div id="logout">로그아웃</div>
		<div id="mypost">작성글 보기</div>
	</div>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		// jQuery를 이용하여 토글 함수 구현

		$(document)
				.ready(
						function() {
							var sidebar = $("#mySidenav");
							var isOpen = false; // 사이드바 상태를 나타내는 변수

							// 사이드바를 열거나 닫는 함수
							function toggleSidebar() {
								if (!isOpen) { // 사이드바가 닫혀있는 경우
									sidebar.css("display", "block"); // 사이드바를 보이도록 설정
									sidebar.css("width", "0"); // 초기 너비를 0으로 설정

									setTimeout(function() {
										sidebar.css("width", "15%"); // 사이드바의 너비 설정
									}, 100); // 사이드바가 나타나는 동안 약간의 딜레이를 줍니다.  
									isOpen = true; // 사이드바 상태를 열림으로 변경
								} else { // 사이드바가 열려있는 경우
									sidebar.css("width", "0"); // 사이드바를 숨김
									setTimeout(function() {
										sidebar.css("display", "none"); // 사이드바를 숨김
									}, 500); // transition 속도와 일치시킵니다.
									isOpen = false; // 사이드바 상태를 닫힘으로 변경
								}
							}

							$("#userbtn").click(function() {
								toggleSidebar();
							});

							$("#homebtn")
									.click(
											function() {
												// 홈 페이지로 이동
												window.location.href = "${pageContext.request.servletContext.contextPath}/jsp/main.do"; // 변경하고자 하는 페이지의 경로로 수정해주세요
											});

							$("#postbtn")
									.click(
											function() {
												window.location.href = "${pageContext.request.servletContext.contextPath}/jsp/postRegister.do";
											});

							$("#logout")
									.click(
											function() {
												window.location.href = "../com.foodie.servlet/LogoutServlet.java";
											})

							$("#mypost")
									.click(
											function() {
												window.location.href = "${pageContext.request.servletContext.contextPath}/jsp/myPost.do";
											})
						});
	</script>

</body>
</html>