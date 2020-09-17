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
		location.href="/index/adminQnA"
	}
})
</script>
</head>
<body>
	<div class="allwrap">
		<div class="wrap">
			<header class="header">
				<jsp:include page="../header/sub.jsp" />
			</header>
			<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>관리자페이지</li>
							<li>》</li>
							<li>고객 Q&A</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">고객 Q&A</h3>
					</div>
				</div>
				<div class="titleForm">
					<div class="btn">
						<c:if test="${question.writer==userId}">
							<button onclick="updateQuestion(${question.id})" id="updateBtn">수정</button>
							<button onclick="deleteQuestion(${question.id})" id="deleteBtn">삭제</button>
						</c:if>
					</div>
					<table>
						<tr>
							<td class="dmq">제목</td>
							<td>${question.title}</td>
						</tr>
						<tr>
							<td class="dmq">문의사항</td>
							<td>${question.division }</td>
						</tr>
						<tr>
							<td colspan="2" class="dmq">내용</td>
						</tr>
						<tr>
							<td colspan="2"><pre>${question.message}</pre></td>
						</tr>
					</table>
				</div>
				<form action="/question/addAdminAnswer?pageNum="
					${answerView.currentPageNum} method="post" class="anwer">
					<div class="comment_main">
						<div>
							<p>댓글달기</p>
							<input type="number" class="hide" value="${question.id}"
								readonly="readonly" name="questionId"> <input
								type="text" name="title" class="hide" value="XX">
							<textarea rows="20" cols="20" name="message" class="commentBox"></textarea>
						</div>
						<div>
							<input type="submit" value="작성" class="writeBtn">
						</div>
					</div>
				</form>
				<div class="answerForm">
					<div class="reComment">댓글</div>
					<table class="">
						<c:forEach var="i" items="${answerView.answerList }"><tr>
								<td class="hide">${i.title}</td>
								<td class="comWriter">${i.writer }</td>
								<td class="comWriter">${i.message}</td>
								<c:if test="${i.writer == userId}">
									<td class="answerBtn"><button onclick="goUpdateAnswer(${i.id})">수정</button>
									<button onclick="goDeleteAnswer(${i.id})">삭제</button></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="pageForm">
				<!-- 현재 페이지가 총 페이지 수와 보다 작거나 같으면 -->
				<c:if
					test="${answerView.currentPageNum<answerView.pageTotalCount+1}">
					<!-- 현재 페이지가 1보다 크고 현재 페이지가 총 페이지의 수보다 작거나 같으 -->
					<c:if
						test="${answerView.currentPageNum>1 && answerView.currentPageNum<=answerView.pageTotalCount}">
						<!-- get 방식의 get요청(인자로 현재 페이지의 전번 페이지로 이동) -->
						<a
							href="/question/detailAdminQuestion?id=${question.id}&pageNum=${answerView.currentPageNum-1}">이전</a>
					</c:if>

					<!-- 만약 현재 페이지가 1이면 -->
					<c:if test="${answerView.currentPageNum==1}">

						<!-- 1부터 페이지 총 수 만큼 반복문을 돌리면서 -->
						<c:forEach var="k" begin="1" end="${answerView.pageTotalCount }">
							<!-- 1~5까지 출력  get 방식의 get요청(인자로 각 수의 페이지 로)-->
							<c:if test="${k<6}">
								<a
									href="/question/detailAdminQuestion?id=${question.id}&pageNum=${k}">${k}</a>
							</c:if>
						</c:forEach>

					</c:if>
					<!-- 현제 페이지가 1이 아니면 -->
					<c:if test="${answerView.currentPageNum!=1}">

						<c:if
							test="${answerView.pageTotalCount-answerView.currentPageNum >=3 && answerView.currentPageNum !=1 }">
							<c:forEach var="j" begin="${answerView.currentPageNum-1}"
								end="${answerView.currentPageNum+3}">
								<a
									href="/question/detailAdminQuestion?id=${question.id}&pageNum=${j}">${j}</a>
							</c:forEach>
						</c:if>

						<c:if
							test="${answerView.pageTotalCount-answerView.currentPageNum<3}">
							<c:if
								test="${(answerView.currentPageNum-(4-(answerView.pageTotalCount-answerView.currentPageNum)))<0}">
								<c:forEach var="j" begin="0" end="${answerView.pageTotalCount }">
									<c:if test="${j>0 }">
										<a
											href="/question/detailAdminQuestion?id=${question.id}&pageNum=${j}">${j}</a>
									</c:if>
								</c:forEach>
							</c:if>

							<c:if
								test="${(answerView.currentPageNum-(4-(answerView.pageTotalCount-answerView.currentPageNum)))>=0}">
								<c:forEach var="j"
									begin="${answerView.currentPageNum-(4-(answerView.pageTotalCount-answerView.currentPageNum))}"
									end="${answerView.pageTotalCount }">
									<c:if test="${j>0 }">
										<a
											href="/question/detailAdminQuestion?id=${question.id}&pageNum=${j}">${j}</a>
									</c:if>
								</c:forEach>
							</c:if>
						</c:if>
					</c:if>

					<c:if
						test="${answerView.currentPageNum <answerView.pageTotalCount }">
						<a
							href="/question/detailAdminQuestion?id=${question.id}&pageNum=${answerView.currentPageNum+1}">다음</a>
					</c:if>
				</c:if>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("myPage","myPageSub")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>