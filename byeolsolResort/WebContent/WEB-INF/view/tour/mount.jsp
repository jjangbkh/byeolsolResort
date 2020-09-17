<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%Random random = new Random();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별솔리조트</title>
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/mount.css">
<script src="https://kit.fontawesome.com/c945c12587.js" crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/script/sub.js"></script>
<script type="text/javascript" src="/script/updateImg.js"></script>
<script type="text/javascript">
window.addEventListener("DOMContentLoaded",function(){
	
	var errorMessage = "${errorMessage}";
	if(errorMessage != ""){
		alert(errorMessage)
		location.href="/index/mount"
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
			<div id="headerblank"></div>
				<img class="subbanner" src="https://gyonewproject.000webhostapp.com/byeolsolResort/moun/moun2.jpg?<%=random.nextInt(500)%>">
			<section>
				<div class="writ">
					<div id="secheader">
            <div class="route">
              <ul>
                <li>HOME</li>
                <li>》</li>
                <li>주변관광지</li>
                <li>》</li>
                <li>등산코스</li>
              </ul>
            </div>
            <div class="sibtitle">
              <h3 class="stitle">등산코스</h3>
            </div>
          </div>
          <div id="allhiking">
            <div class="hikingtitle">
              가슴이 탁 트이는 산행, 별솔이 추천하는 등산 코스
            </div>
            <div class="hikinginfo" style="border-bottom: 1px solid #E4E1E1;">
              <p>리조트에서<span class="blue"> 바로 </span>이어지는 편리한 등산을 즐겨보세요!</p><br>
              두릉산에서 팔봉산으로 이어지는 등산 코스는 홍천의 명물입니다.<br>
              별솔은 각 계절에 맞는 등산 이벤트를 상시로 개최하고 있습니다. 놓치지 말고 즐거운 추억을 만들어 보십시오.
            </div>
            <div class="mountainTitle">
              <i class="fas fa-mountain"></i> 소개
            </div>
						<div class="hikinglist">
              <div class="mountain">
                <div class="mountainimgs">
                  <div class="imgshadow"></div>
                  <div class="imgtext">두릉산 자연휴양림</div>
                  <img class="mountainImg" src="https://gyonewproject.000webhostapp.com/byeolsolResort/moun/drung.jpg" alt="">
                </div>

                <div class="mountainInfo1">
                  <div class="drung">
                    두릉산
                  </div>
                  별솔의 가족사랑동 뒤켠으로 돌아가시면 두릉산으로 바로 연결된 산책로가 존재합니다.<br>
                  잘 정비된 길과 울타리를 따라가시면 곧바로 코스가 설명된 표지판을 볼 수 있습니다.<br>
                  난이도별로 나누어진 코스를 확인하시고, 별솔과 함께 부담없는 산행을 즐겨보세요.<br><br>
                  <p class="bold">봄, 가을에 방문하시면 더욱 특별한 풍경을 감상하실 수 있습니다.</p>
                  <div class="events">
                    <p><i class="fab fa-pagelines"></i> 봄: 매년 2~3월 봄꽃맞이절</p>
                    <p><i class="fab fa-pagelines"></i> 가을: 매년 9~10월 밤밤낙엽절</p>
                  </div>

                  <br>
                  <p><span class="span">A코스</span><span class="blues">｜</span>&nbsp;&nbsp;소요시간 120분 내외</p>
                  <p><span class="span">B코스</span><span class="blues">｜</span>&nbsp;&nbsp;소요시간 90분 내외</p>
                  <p><span class="span">C코스</span><span class="blues">｜</span>&nbsp;&nbsp;소요시간 40분 내외</p>
                  <div class="trees">
                    <i class="fas fa-tree small"></i><i class="fas fa-tree"></i>
                  </div>
                </div>
              </div>
                <hr>
                <div class="mountain">
                  <div class="mountainInfo2">
                    <div class="drung">
                      팔봉산
                    </div>
                    한국의 100대 명산중 하나로 당당히 이름을 올리고 있는 팔봉산은 홍천의 명물입니다.<br>
                    봉우리가 8개라 하여 팔봉산이라는 이름이 붙여진 이 산은 고도가 낮음에도<br>
                    무척이나 아름다운 경관을 가지고 있습니다.<br>
                    기암과 절벽 사이의 등산로를 통해 산의 절경을 느껴보세요.<br><br>
                    <p class="bold">푸르른 홍천강과 강의 백사장 역시 뛰어난 볼거리입니다.</p>


                    <br>
                    <div class="left">
                      <p><span class="span">1코스</span><span class="blues">｜</span>&nbsp;&nbsp;소요시간 150분 내외</p>
                      <p><span class="span">2코스</span><span class="blues">｜</span>&nbsp;&nbsp;소요시간 120분 내외</p>
                      <p><span class="span">3코스</span><span class="blues">｜</span>&nbsp;&nbsp;소요시간 90분 내외</p>
                      <p><span class="span">4코스</span><span class="blues">｜</span>&nbsp;&nbsp;소요시간 60분 내외</p>
                    </div>
                    <div class="trees2">
                      <i class="fas fa-tree small"></i><i class="fas fa-tree"></i>
                    </div>
                  </div>
                  <div class="mountainimgs">
                    <div class="imgshadow"></div>
                    <div class="imgtext">팔봉산 제 8봉우리</div>
                    <img class="mountainImg" src="https://gyonewproject.000webhostapp.com/byeolsolResort/moun/palbong.jpg" alt="">
                  </div>
                </div>
              <hr>
            <div class="course">
              두릉산 산책로와 팔봉산 등산로가 연결되어 있어 더욱 편안한 산행이 가능합니다.<br>
               두릉산 산책로 중앙의 무인 카페도 잊지 말고 방문해 보세요!
            </div>
          </div>
</div>

				</div>	<!-- writ -->
			</section>
			<footer>
				<jsp:include page="../footer/footer.jsp"/>
			</footer>
		</div>
		<c:if test='${userId=="admin"}'>
		<button onclick='updateImg("moun","moun2")'>배너이미지 수정</button>
		</c:if>
	</div>
</body>
</html>