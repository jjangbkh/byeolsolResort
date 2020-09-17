<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="jt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%Random random = new Random(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<script src="https://kit.fontawesome.com/c945c12587.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/css/notice.css">
<link rel="stylesheet" href="/css/board1.css">
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/footer.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/news.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/board/adminList"
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
			<div>
				<img class="subimage"
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/board/byeolsolnewssub.jpg?<%=random.nextInt(500)%>">
			</div>
			<section>
				<div class="writ">
					<div class="route" style="padding: 0;">
						<ul style="padding-left: 700px;">
							<li>HOME</li>
							<li>》</li>
							<li>별솔소식</li>
							<li>》</li>
							<li>별솔뉴스</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">별솔뉴스</h3>
					</div>

					<!-- 만약 정보의 총 수가 0 보다 크면 -->
					<c:if test="${boardView.boardCnt>0 }">
						<!-- request로 받은 boardView의 정보리스트를 item으로 잡고 -->
						<div class="notice_main">
							<div class="tableborder">
								<div class="keyword_nameBox">
									<div>Search</div>
									<input type="text" id="keyword_name">
								</div>
								<table id="user_list">
									<tbody style="border-bottom: 2px solid #969696;">
										<tr class="notice_top">
											<td class="tablenumber">번호</td>
											<td class="tableside">작성자</td>
											<td class="tablemain">제목</td>
											<td class="tableside">게시날짜</td>
										</tr>
										<c:forEach var="q" items="${boardView.boardList}">
											<tr class="border" onclick="goDetail(${q.id})">
												<td>${q.id}</td>
												<td>별솔</td>
												<td><b>${q.title}</b></td>
												<td><jt:format value="${q.wDate}"
														pattern="YYYY-MM-dd HH:mm:ss" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="numbermap">
									<c:if
										test="${boardView.currentPageNum<boardView.pageTotalCount+1}">
										<!-- 현재 페이지가 1보다 크고 현재 페이지가 총 페이지의 수보다 작거나 같으 -->
										<c:if
											test="${boardView.currentPageNum>1 && boardView.currentPageNum<=boardView.pageTotalCount}">
											<!-- get 방식의 get요청(인자로 현재 페이지의 전번 페이지로 이동) -->
											<a
												href="/board/adminList?pageNum=${boardView.currentPageNum-1}"><i
												class="fas fa-angle-left"></i></a>
										</c:if>
										<!-- 만약 현재 페이지가 1이면 -->
										<c:if test="${boardView.currentPageNum==1}">
											<!-- 1부터 페이지 총 수 만큼 반복문을 돌리면서 -->
											<c:forEach var="k" begin="1"
												end="${boardView.pageTotalCount }">
												<!-- 1~5까지 출력  get 방식의 get요청(인자로 각 수의 페이지 로)-->
												<c:if test="${k<6}">
													<a href="/board/adminList?pageNum=${k}">${k}</a>
												</c:if>
											</c:forEach>
										</c:if>
										<!-- 현제 페이지가 1이 아니면 -->
										<c:if test="${boardView.currentPageNum!=1}">
											<c:if
												test="${boardView.pageTotalCount-boardView.currentPageNum >=3 && boardView.currentPageNum !=1 }">
												<c:forEach var="j" begin="${boardView.currentPageNum-1}"
													end="${boardView.currentPageNum+3}">
													<a href="/board/adminList?pageNum=${j}">${j}</a>
												</c:forEach>
											</c:if>
											<c:if
												test="${boardView.pageTotalCount-boardView.currentPageNum<3}">
												<c:if
													test="${(boardView.currentPageNum-(4-(boardView.pageTotalCount-boardView.currentPageNum)))<0}">
													<c:forEach var="j" begin="0"
														end="${boardView.pageTotalCount }">
														<c:if test="${j>0 }">
															<a href="/board/adminList?pageNum=${j}">${j}</a>
														</c:if>
													</c:forEach>
												</c:if>
												<c:if
													test="${(boardView.currentPageNum-(4-(boardView.pageTotalCount-boardView.currentPageNum)))>=0}">
													<c:forEach var="j"
														begin="${boardView.currentPageNum-(4-(boardView.pageTotalCount-boardView.currentPageNum))}"
														end="${boardView.pageTotalCount }">
														<c:if test="${j>0 }">
															<a href="/board/adminList?pageNum=${j}">${j}</a>
														</c:if>
													</c:forEach>
												</c:if>
											</c:if>
										</c:if>
										<c:if
											test="${boardView.currentPageNum <boardView.pageTotalCount }">
											<a
												href="/board/adminList?pageNum=${boardView.currentPageNum+1}"><i
												class="fas fa-angle-right"></i></a>
										</c:if>
									</c:if>
								</div>
							</div>
						</div>
					</c:if>
					<br>
					<div>
						<c:if test='${userId=="admin"}'>
							<button id="addAdminBoard" onclick="addAdminBoard()">글쓰기</button>
						</c:if>
					</div>
				</div>
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp" />
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
			<button onclick='updateImg("board","byeolsolnewssub")'>배너이미지
				수정</button>
		</c:if>
	</div>
</body>
</html>