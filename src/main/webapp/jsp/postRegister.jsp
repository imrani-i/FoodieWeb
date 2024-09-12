<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	$(document).ready(function() {

		$("#cancelbtn").click(function() {
			window.location.href = "main.jsp";
		});
	});
	
	$('#search').click(function() {
	    // AJAX 요청 보내기
	    $.ajax({
	        url: '${pageContext.request.servletContext.contextPath}/jsp/postRegister.do',
	        type: 'GET',
	        success: function(data) {
	          
	            createPopupList(data);
	        },
	        error: function() {
	            alert('데이터를 가져오는 데 문제가 발생했습니다.');
	        }
	    });
	});

	// 받은 데이터를 이용하여 팝업 창에 리스트 생성하는 함수
	function createPopupList(data) {
	    var popupContent = '<ul>'; // 리스트를 감싸는 <ul> 요소 생성
	    
	    // 받은 데이터를 이용하여 각 가게 정보를 리스트 아이템으로 추가
	    $.each(data, function(index, restaurant) {
	        popupContent += '<li>' + restaurant.name + '</li>'; // 예시로서 'name' 필드를 사용
	    });
	    
	    popupContent += '</ul>'; // 리스트를 닫는 </ul> 요소 추가
	    
	    // 팝업 창에 리스트 표시
	    // 예시로서 alert() 함수를 사용하여 간단히 표시하도록 하였습니다.
	    alert(popupContent);
	}
	
	
</script>


<style>
#search {
	flex: 0 0 auto;
	width: 10%;
	margin-right: 0;
	padding: 12px;
	border-radius: 5px;
	border: none;
}

.all_view {
	padding-top:10%;
	overflow: hidden;
	height: 100%;
	width: 50%;
	margin: 0 auto;
	
	align-items: center;
	text-align: center;
	position: relative;

	
}

.registerform {
	
	width: 100%;
	
}

.input-container {
	margin-top: 5px;
	margin-bottom: 30px;
	display: flex;
	align-items: center; /* 수직 가운데 정렬 */
}

.register {

	position: fixed;
    bottom: 8%; /* 하단 메뉴 바로 위에 위치 */
    left: 0;
    width: 100%;
  
    margin-top: 0;


} 
.submitbtn {
	display: inline-block;
	width: 48%;
	padding: 12px;
	border-radius: 5px;
	border: none;
}

label {
	flex: 0 0 15%; /* 레이블 너비 설정 */
}

/* 입력 필드 스타일 */
input, select, textarea {
	flex: 1;
	display: inline-block; /* 인라인 블록 요소로 변경 */
	width: 80%; /* 입력 필드의 너비 조정 */
	padding: 12px;
	margin: 10px auto; /* 수평 가운데 정렬을 위해 자동 마진 설정 */
	margin-right: 30px;
	box-sizing: border-box;
	border-radius: 5px;
	border: 1px solid #CACACA; /* 간단한 테두리 스타일 적용 */
}
</style>
</head>
<body>


	<div class="all_view">

		<form action="postRegister.do" method="post" class="registerform">

			<span>
				<p>맛집 등록</p>
			</span>

			<div class="input-container">
				<label for="text" style="font-size: 13px;">가게명:</label> <input
					type="text" id="restarant" name="restarant">
				<button id="search">검색</button>

			</div>


			<div class="input-container">
				<!--   <label for="category" class="category_label"
					style="font-size: 13px;">카테고리:</label> <select
					class="form-category" id="category" name="category">

					<option>한식</option>
					<option>일식</option>
					<option>중식</option>
					<option>양식</option>
					<option>분식</option>
					<option>아시아</option>
					<option>패스트푸드</option>
					<option>기타</option>
				-->
				</select> <label for="grade" class="grade_label" style="font-size: 13px;">평점:</label>
				<select class="form-grade" id="grade" name="grade">

					<option value="5">★★★★★</option>
					<option value="4">★★★★</option>
					<option value="3">★★★</option>
					<option value="2">★★</option>
					<option value="1">★</option>
				</select>
			</div>
			<div class="input-container">


				<label for="text" style="font-size: 13px;">추천메뉴:</label> <input
					type="text" id="recommend" name="recommend">
			</div>
			<div class="input-container">
				<label for="comment" style="font-size: 13px;"> 내용:</label>

				<textarea class="comment" rows="3" id="comment" name="text"></textarea>
			</div>

		</form>
		<div class="register">
			<button class="submitbtn" id="cancelbtn">취소</button>
			<button type="submit" class="submitbtn" id="registerbtn">등록</button>
		</div>
		<%@ include file="navBottom.jsp"%>
	</div>

</body>
</html>
