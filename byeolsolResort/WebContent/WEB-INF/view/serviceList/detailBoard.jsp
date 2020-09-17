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
<link rel="stylesheet" href="/css/detailBoard.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/board.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/board/list"
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
					src="https://gyonewproject.000webhostapp.com/byeolsolResort/board/byeolsolnewssub.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div class="route">
						<ul>
							<li>HOME</li>
							<li>》</li>
							<li>회원서비스</li>
							<li>》</li>
							<li>후기게시판</li>
						</ul>
					</div>
					<div class="sibtitle">
						<h3 class="stitle">후기게시판</h3>

					</div>
					<div>
						<div class="upBtn">
							<c:if test='${userId==board.userId || userId=="admin"}'>
								<button onclick="goUpdate(${board.id})" class="update_btn1">수정</button>
							</c:if>

							<c:if test='${userId==board.userId || userId=="admin"}'>
								<button onclick="goDelete(${board.id})" class="update_btn">삭제</button>
							</c:if>
						</div>
						<table>
							<tr>
								<td class="titleForm">제목</td>
								<td class="updateForm">${board.title }</td>
							</tr>
							<tr>
								<td class="titleForm">작성자</td>
								<td class="updateForm">${board.userId}</td>
							</tr>
							<tr>
								<td class="titleForm">내용</td>
								<td class="updateForm">${board.content}</td>
							</tr>
						</table>
					</div>
					<c:if test="${board.firstPath!=null }">
						<div>
							<c:if test="${board.firstPath!=null }">
								<img class="imgForm" src="${board.firstPath }">
							</c:if>
							<c:if test="${board.secondPath!=null }">
								<img class="imgForm" src="${board.secondPath }">
							</c:if>
							<c:if test="${board.thirdPath!=null }">
								<img class="imgForm" src="${board.thirdPath }">
							</c:if>
						</div>
					</c:if>
					<div>
						<div class="tableDiv">
							<form action="/board/addComment" method="post">
								<table>
									<tr>
										<td class="addCom1">댓글달기</td>
										<td class="hide"><input readonly="readonly" class="hide"
											value="${board.id }" name="boardId"></td>
										<td class="hideTd"><input type="text" name="message"
											class="update_comment"></td>
										<td class="subBtn"><input type="submit"
											id="update_subBtn" value="등록"></td>
									</tr>
								</table>

							</form>
						</div>
						<div class="comment">댓글</div>
						<div>
							<table>
								<c:if test="${commentView.commentCnt >0 }">
									<tr>
										<td class="users">작성자</td>
										<td colspan="3">내용</td>
									</tr>
									<c:forEach var="q" items="${commentView.commentList }">
										<tr>
											<td>${q.userId}</td>
											<td>${q.message }</td>
											<c:if test='${userId==q.userId || userId=="admin"}'>
												<td class="updel">
													<button class="updelCom" type="button"
														onclick="onUpdate(${q.id},${board.id})">수정</button>
												</td>
												<td class="updel">
													<button class="updelCom1" type="button"
														onclick="onDelte(${q.id},${board.id})">삭제</button>
												</td>
											</c:if>
										</tr>
									</c:forEach>
								</c:if>
							</table>
						</div>
						<!-- 현재 페이지가 총 페이지 수와 보다 작거나 같으면 -->
						<c:if
							test="${commentView.currentPageNum<commentView.pageTotalCount+1}">
							<!-- 현재 페이지가 1보다 크고 현재 페이지가 총 페이지의 수보다 작거나 같으 -->
							<c:if
								test="${commentView.currentPageNum>1 && commentView.currentPageNum<=commentView.pageTotalCount}">
								<!-- get 방식의 get요청(인자로 현재 페이지의 전번 페이지로 이동) -->
								<a
									href="/board/detailBoard?pageNum=${commentView.currentPageNum-1}&boardId=${board.id}">이전</a>
							</c:if>

							<!-- 만약 현재 페이지가 1이면 -->
							<c:if test="${commentView.currentPageNum==1}">

								<!-- 1부터 페이지 총 수 만큼 반복문을 돌리면서 -->
								<c:forEach var="k" begin="1"
									end="${commentView.pageTotalCount }">
									<!-- 1~5까지 출력  get 방식의 get요청(인자로 각 수의 페이지 로)-->
									<c:if test="${k<6}">
										<a href="/board/detailBoard?pageNum=${k}&boardId=${board.id}">${k}</a>
									</c:if>
								</c:forEach>
							</c:if>
							<!-- 현제 페이지가 1이 아니면 -->
							<c:if test="${commentView.currentPageNum!=1}">

								<c:if
									test="${commentView.pageTotalCount-commentView.currentPageNum >=3 && commentView.currentPageNum !=1 }">
									<c:forEach var="j" begin="${commentView.currentPageNum-1}"
										end="${commentView.currentPageNum+3}">
										<a href="/board/detailBoard?pageNum=${j}&boardId=${board.id}">${j}</a>
									</c:forEach>
								</c:if>
								<c:if
									test="${commentView.pageTotalCount-commentView.currentPageNum<3}">
									<c:if
										test="${(commentView.currentPageNum-(4-(commentView.pageTotalCount-commentView.currentPageNum)))<0}">
										<c:forEach var="j" begin="0"
											end="${commentView.pageTotalCount }">
											<c:if test="${j>0 }">
												<a
													href="/board/detailBoard?pageNum=${j}&boardId=${board.id}">${j}</a>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if
										test="${(commentView.currentPageNum-(4-(commentView.pageTotalCount-commentView.currentPageNum)))>=0}">
										<c:forEach var="j"
											begin="${commentView.currentPageNum-(4-(commentView.pageTotalCount-commentView.currentPageNum))}"
											end="${commentView.pageTotalCount }">
											<c:if test="${j>0 }">
												<a
													href="/board/detailBoard?pageNum=${j}&boardId=${board.id}">${j}</a>
											</c:if>
										</c:forEach>
									</c:if>
								</c:if>
							</c:if>
							<c:if
								test="${commentView.currentPageNum <commentView.pageTotalCount }">
								<a
									href="/board/detailBoard?pageNum=${commentView.currentPageNum+1}&boardId=${board.id}">다음</a>
							</c:if>
						</c:if>

						<c:if test="${commentView.commentCnt<=0 }">

					정보가 없습니다.
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