<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%Random random = new Random(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/myQnA.css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/myQnA.js"></script>
<script type="text/javascript" src="/script/detailMyQnA.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/question/list"
	}
})
</script>
</head>
<body>
	<div class="allwrap">
		<div class="wrap">
			<header class="header">
				<jsp:include page="../header/sub.jsp"/>
			</header>
			<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/question/questionSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>마이페이지</li>
							<li>》</li>
							<li>나의 Q&A</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">나의 Q&A</h3>
					</div>
				</div>
				<form action="/question/updateQuestion" method="post">
					<table class="updateQnATable">
						<tr class="number">
							<td><input type="number" class="hide" value="${question.id}" name="id" readonly="readonly"></td>
						</tr>
						<tr>
							<td class="formTd1">제목</td>
							<td class="formTd2"><input type="text" id="qNa_title" name="title"
								value="${question.title}"></td>
						</tr>
						<tr>
							<td class="formTd1">분류</td>
							<td class="formTd2"><select name="division">
									<option value="예약">예약문의</option>
									<option value="객실">객실문의</option>
									<option value="식음시설">식음시설</option>
									<option value="관광정보">관광정보</option>
									<option value="기타">기타</option>
							</select></td>
						</tr>
						<tr>
							<td class="formTd1">내용</td>
							<td class="formTd2"><textarea id="textarea" rows="8" cols="80"
									name="message">${question.message }</textarea></td>
						</tr>
					</table>
					<div class="submit">
						<input type="submit" class="qNa_submit" value="수정">
					</div>
				</form>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("question","questionSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>