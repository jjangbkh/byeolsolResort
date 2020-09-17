<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>별솔리조트</title>
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/trashCan.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="/script/trashCan.js"></script>
</head>

<body>
    <div class="allwrap">
        <div class="wrap">
            <header class="header">
                <jsp:include page="../header/sub.jsp" />
            </header>
            <div class="subimage">
                <h2>서브 이미지 들어가야함.</h2>
            </div>
            <section>
                <div class="writ">
                    <div class="route">
                        <ul>
                            <li>HOME</li>
                            <li>》</li>
                            <li>관리자페이지</li>
                            <li>》</li>
                            <li>삭제정보관리</li>
                        </ul>
                    </div>
                    <div class="sibtitle">
						<h3 class="stitle">삭제정보관리</h3>
					</div>
                    <div class="content_foot">
						<ul class="flotBox_my_tap">
							<li><a href="/cus/adminUserInfo" class="li3">유저정보관리</a></li>
							<li><a href="/index/adminRoom" class="li2">객실예약관리</a></li>
							<li><a href="/index/adminQnA" class="li4">Q&A관리</a></li>
							<li><a href="/reserv/adminRemovePage" class="li1">삭제정보관리</a></li>
						</ul>
					</div>
                    <div class="trash_main">
                        <div class="trash_search">
                            이름 : <input type="text" id="trash_name">
                        </div>
                        <div class="trash_table">
                            <table id="trash_list">
                                <thead>
                                    <tr>
                                        <td>예약번호</td>
                                        <td>예약ID</td>
                                        <td>객실번호</td>
                                        <td>입실날짜</td>
                                        <td>퇴실날짜</td>
                                        <td>금액</td>
                                        <td>예약자명</td>
                                        <td>연락처</td>
                                        <td>상태</td>
                                        <td>삭제</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    	<c:forEach var="rm" items="${removeView.removeList }">
                                    <tr>
                                        <td>${rm.id }</td>
                                        <td>${rm.userId }</td>
                                        <td>${rm.roomId }</td>
                                        <td>${rm.startDate }</td>
                                        <td>${rm.endDate }</td>
                                        <td>${rm.totalPrice }</td>
                                        <td>${rm.userName }</td>
                                        <td>${rm.userPhone }</td>
                                        <td>${rm.state }</td>
                                        <td><input type="button" value="삭제" class="deleteBtn" onclick="deleteRemove(${rm.id})"></td>
                                    </tr>
                                        </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </section>
            <footer>
                <jsp:include page="../footer/footer.jsp" />
            </footer>
        </div>
    </div>
</body>

</html>