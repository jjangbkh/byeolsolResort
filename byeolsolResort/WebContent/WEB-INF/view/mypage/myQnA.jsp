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
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
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
			<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%=random.nextInt(500)%>">
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
				<form onsubmit="return check()" id="regis">
					<div class="content_foot">
						<ul class="flotBox_my_tap">
							<li><a href="/cus/myPage" class="li2">개인정보</a></li>
							<li><a href="/question/list" class="li1">나의 Q&A</a></li>
							<li><a href="/cus/myReserv" class="li3">나의 예약</a></li>
						</ul>
					</div>
					<table>
					<tr class="myQnAtr">
						<td>작성자</td> 
						<td>제목</td>
						<td>내용</td>
					</tr>
					<c:forEach var="i" items="${questionView.questionList}">
					<tr onclick="goDetail(${i.id})" class="qNaUpForm">
						<td><a href='/question/detailQuestion?id=${i.id }'>${i.writer }</a></td>
						<td>${i.title }</td>
						<td>${i.message }</td>
					</tr>
					</c:forEach>
					</table>
					<div class="myQnAPagenation">
					<!-- 현재 페이지가 총 페이지 수와 보다 작거나 같으면 -->
					<c:if test="${questionView.currentPageNum<questionView.pageTotalCount+1}">
					<!-- 현재 페이지가 1보다 크고 현재 페이지가 총 페이지의 수보다 작거나 같으 -->
					<c:if test="${questionView.currentPageNum>1 && questionView.currentPageNum<=questionView.pageTotalCount}">
					<!-- get 방식의 get요청(인자로 현재 페이지의 전번 페이지로 이동) -->
					<a href="/question/list?pageNum=${questionView.currentPageNum-1}">이전</a>
					</c:if>
					
					<!-- 만약 현재 페이지가 1이면 -->
					<c:if test="${questionView.currentPageNum==1}">
					
					
					<!-- 1부터 페이지 총 수 만큼 반복문을 돌리면서 -->
					<c:forEach var="k" begin="1" end="${questionView.pageTotalCount }">
					<!-- 1~5까지 출력  get 방식의 get요청(인자로 각 수의 페이지 로)-->
					<c:if test="${k<6}">
					<a href="/question/list?pageNum=${k}">${k}</a>
					</c:if>
					</c:forEach>
					
					</c:if>
					<!-- 현제 페이지가 1이 아니면 -->
					<c:if test="${questionView.currentPageNum!=1}">
					
					<c:if test="${questionView.pageTotalCount-questionView.currentPageNum >=3 && questionView.currentPageNum !=1 }">
					<c:forEach var="j" begin="${questionView.currentPageNum-1}" end="${questionView.currentPageNum+3}">
					<a href="/question/list?pageNum=${j}">${j}</a>
					</c:forEach>
					</c:if>
					
					<c:if test="${questionView.pageTotalCount-questionView.currentPageNum<3}">
					<c:if test="${(questionView.currentPageNum-(4-(questionView.pageTotalCount-questionView.currentPageNum)))<0}">
					<c:forEach var="j" begin="0" end="${questionView.pageTotalCount }">
					<c:if test="${j>0 }">
					<a href="/question/list?pageNum=${j}">${j}</a>
					</c:if>
					</c:forEach>
					 </c:if>
					 
					 <c:if test="${(questionView.currentPageNum-(4-(questionView.pageTotalCount-questionView.currentPageNum)))>=0}">
					 <c:forEach var="j" begin="${questionView.currentPageNum-(4-(questionView.pageTotalCount-questionView.currentPageNum))}" end="${questionView.pageTotalCount }">
					<c:if test="${j>0 }">
					<a href="/question/list?pageNum=${j}">${j}</a>
					</c:if>
					</c:forEach>
					 </c:if>
					 </c:if>
					 </c:if>
					
					<c:if test="${questionView.currentPageNum <questionView.pageTotalCount }">
					<a href="/question/list?pageNum=${questionView.currentPageNum+1}">다음</a>
					</c:if>
					</c:if>
					</div>
				</form>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("myPage","myPageSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>