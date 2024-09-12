<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.List"%>
<%@page import="com.foodie.post.PostDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    // 버튼 클릭 이벤트 핸들러
    function handleDeleteButtonClick(postNo) {
        $.ajax({
            type: "POST",
            url: '${pageContext.request.servletContext.contextPath}/jsp/myPost.do',
            contentType: 'application/json',
            data: JSON.stringify({ "postNo": postNo }),
            success: function(response) {
                // 클라이언트에서 삭제된 게시물의 ID를 받아와서 해당 요소를 삭제
                const deletedPostId = response;
                const deletedPostElement = document.querySelector('#post-' + deletedPostId);
                console.log(deletedPostId);
                if (deletedPostElement) {
                    deletedPostElement.remove();
                }
                // 목록 업데이트
                updatePostList();
            },
            error: function() {
                console.error('게시물 삭제 실패');
            }
        });
    }

    // 목록 업데이트 함수
    function updatePostList() {
        $.ajax({
            type: "GET",
            url: '${pageContext.request.servletContext.contextPath}/jsp/myPost.do',
            success: function(response) {
                // 서버로부터 받아온 HTML을 현재 목록에 적용
                $('.new').html(response);
            },
            error: function() {
                console.error('목록 업데이트 실패');
            }
        });
    }

    // 모든 삭제 버튼에 클릭 이벤트 핸들러를 할당
    $(document).ready(function() {
        $('.post-delete-btn').click(function() {
            const postNo = $(this).data('postid');
            handleDeleteButtonClick(postNo); // 삭제 버튼 클릭 핸들러 호출
        });
    });
</script>





<style>
ul {
	list-style-type: none;
	padding-left: 0; /* 리스트들의 패딩을 없애기 위해 추가 */
}

ul li {
	margin-bottom: 20px;
}

.all_view {
	background-color: #FFFFF6;
	width: 100%;
	margin: 0 auto;
	align-items: center;
	height: 100%;
	text-align: center;
	position: relative;
	overflow-y: hidden
}

.scroll-container {
	background: white;
	overflow-y: auto;
	width: 100%;
	height: 100vh; /* 세로 스크롤을 표시합니다. 내용이 넘칠 때만 스크롤이 표시됩니다. */
}

.post-item {
	background: white;
	display: block;
	margin-left: 10%;
	margin-right: 10%;
	position: relative;
	padding-top: 20px;
	padding-left: 2%;
	padding-right: 2%;
}

.post-no {
	font-color: #747474;
	font-size: 13px;
	margin-left: 10px;
	position: absolute; /* 포지션을 절대 위치(absolute position)로 설정 */
	top: 0; /* 부모 요소 상단으로부터의 거리 */
	left: 0; /* 부모 요소 왼쪽으로부터의 거리 */
}

.post-name {
	font-size: 13px;
	display: block; /* 한 줄에 한 항목만 표시 */
	text-align: left; /* 가운데 정렬 */
	margin-bottom: 15px; /* 각 항목 사이의 간격 설정 */
	margin-top: 10px;
}

.post-content {
	font-size: 13px;
	display: block; /* 한 줄에 한 항목만 표시 */
	text-align: left; /* 가운데 정렬 */
	margin-top: 7px;
	margin-bottom: 20px;
}

.post-date {
	font-color: #747474;
	font-size: 13px;
	position: absolute; /* 포지션을 절대 위치(absolute position)로 설정 */
	top: 0; /* 부모 요소 상단으로부터의 거리 */
	right: 0;
}

.post-delete-btn {
	border: none;
	border-radius: 15%;
	padding: 4px;
}
</style>
</head>
<body>
<div class="new">
	<div class="all_view">
		<%
		String writer = (String) request.getAttribute("writer");
		%>
		<p><%=writer%>님이 작성한 글 목록
		</p>
		
		<div class="scroll-container">

			<ul>
				<%
				for (PostDTO post : (List<PostDTO>) request.getAttribute("postList")) {
				%>
				<li class="post-item"><span class="post-no">No.<%=post.getPostNo()%></span>
					<span class="post-name"> <img src="../image/home.png"
						width="13px" style="margin-right: 3px;"> <%=post.getRestaurantPname()%>
						<%
						int grade = post.getGrade(); // JavaScript로 넘겨줄 때는 자바 변수를 int 타입으로 받아야 합니다.
						String stars = "";
						for (int i = 0; i < grade; i++) {
							stars += "★";
						}
						%> <span><%=stars%></span> <span class="post-content"> <span
							style="font-style: italic;">comment : </span><%=post.getContent()%></span>
						<span class="post-date">
							<%
							LocalDateTime dateTime = post.getTime();
							%> <%
 LocalDate date = dateTime.toLocalDate();
 %> <%
 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 %> <%=date.format(formatter)%>
							<button type="submit" name="postdelete" class="post-delete-btn"
								style="font-size: 10px;" data-postid="<%=post.getPostNo()%>">
								삭제</button>
					</span>
						<hr class="divider"></li>
				<%
				}
				%>

			</ul>
		</div>
		<%@ include file="navBottom.jsp"%>
</div>
		
	</div>

</body>
</html>