<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Random random = new Random();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/admin.css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/admin.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
	window.addEventListener("DOMContentLoaded", function() {

		var errorMessage = "${errorMessage}";
		if (errorMessage != "") {
			alert(errorMessage)
			location.href = "/cus/adminUserInfo"
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
			<img class="subbanner"
				src="https://gyonewproject.000webhostapp.com/byeolsolResort/myPage/myPageSub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>관리자페이지</li>
							<li>》</li>
							<li>유저정보관리</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">유저정보관리</h3>
					</div>
				</div>
				<form onsubmit="return check()" id="regis">
					<div class="content_foot">
						<ul class="flotBox_my_tap">
							<li><a href="/cus/adminUserInfo" class="li1">유저정보관리</a></li>
							<li><a href="/index/adminRoom" class="li2">객실예약관리</a></li>
							<li><a href="/index/adminQnA" class="li3">Q&A관리</a></li>
							<li><a href="/reserv/adminRemovePage" class="li4">삭제정보관리</a></li>
						</ul>
					</div>
					<div class="user_form">
						<div class="keywordForm">
							이름 : <input type="text" id="keyword_name">
						</div>
						<table id="user_list">
							<thead>
								<tr>
									<td>id</td>
									<td>user_id</td>
									<td>name</td>
									<td>email</td>
									<td>zip_code</td>
									<td>address</td>
									<td>phone</td>
									<td>email_state</td>
									<td>birth_date</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="u" items="${customerView.customerList }">
									<tr>
										<td>${u.id }</td>
										<td>${u.userId }</td>
										<td>${u.name }</td>
										<td>${u.email }</td>
										<td>${u.zipCode }</td>
										<td>${u.address }</td>
										<td>${u.phone }</td>
										<td>${u.emailState }</td>
										<td>${u.birthDate }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="textCenter">
							<!-- 현재 페이지가 총 페이지 수와 보다 작거나 같으면 -->
							<c:if
								test="${customerView.currentPageNum<customerView.pageTotalCount+1}">
								<!-- 현재 페이지가 1보다 크고 현재 페이지가 총 페이지의 수보다 작거나 같으 -->
								<c:if
									test="${customerView.currentPageNum>1 && customerView.currentPageNum<=customerView.pageTotalCount}">
									<!-- get 방식의 get요청(인자로 현재 페이지의 전번 페이지로 이동) -->
									<a
										href="/cus/adminUserInfo?pageNum=${customerView.currentPageNum-1}">이전</a>
								</c:if>

								<!-- 만약 현재 페이지가 1이면 -->
								<c:if test="${customerView.currentPageNum==1}">

									<!-- 1부터 페이지 총 수 만큼 반복문을 돌리면서 -->
									<c:forEach var="k" begin="1"
										end="${customerView.pageTotalCount }">
										<!-- 1~5까지 출력  get 방식의 get요청(인자로 각 수의 페이지 로)-->
										<c:if test="${k<6}">
											<a href="/cus/adminUserInfo?pageNum=${k}">${k}</a>
										</c:if>
									</c:forEach>

								</c:if>
								<!-- 현제 페이지가 1이 아니면 -->
								<c:if test="${customerView.currentPageNum!=1}">
									<c:if
										test="${customerView.pageTotalCount-customerView.currentPageNum >=3 && customerView.currentPageNum !=1 }">
										<c:forEach var="j" begin="${customerView.currentPageNum-1}"
											end="${customerView.currentPageNum+3}">
											<a href="/cus/adminUserInfo?pageNum=${j}">${j}</a>
										</c:forEach>
									</c:if>

									<c:if
										test="${customerView.pageTotalCount-customerView.currentPageNum<3}">
										<c:if
											test="${(customerView.currentPageNum-(4-(customerView.pageTotalCount-customerView.currentPageNum)))<0}">
											<c:forEach var="j" begin="0"
												end="${customerView.pageTotalCount }">
												<c:if test="${j>0 }">
													<a href="/cus/adminUserInfo?pageNum=${j}">${j}</a>
												</c:if>
											</c:forEach>
										</c:if>

										<c:if
											test="${(customerView.currentPageNum-(4-(customerView.pageTotalCount-customerView.currentPageNum)))>=0}">
											<c:forEach var="j"
												begin="${customerView.currentPageNum-(4-(customerView.pageTotalCount-customerView.currentPageNum))}"
												end="${customerView.pageTotalCount }">
												<c:if test="${j>0 }">
													<a href="/cus/adminUserInfo?pageNum=${j}">${j}</a>
												</c:if>
											</c:forEach>
										</c:if>
									</c:if>
								</c:if>

								<c:if
									test="${customerView.currentPageNum <customerView.pageTotalCount }">
									<a
										href="/cus/adminUserInfo?pageNum=${customerView.currentPageNum+1}">다음</a>
								</c:if>
							</c:if>
						</div>
				</form>
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