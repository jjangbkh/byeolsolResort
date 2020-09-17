function dateBtn() {
	var startDate = $("input[name=startDate]").val();
	var endDate = $("input[name=endDate]").val();
	if(startDate != null && endDate!=null){
	$.ajax({
		url:"/reserv/printRoomNum",
		type:"post",
		data :{"startDate":startDate , "endDate" : endDate},
		success : function(d){
			
			for(var i = 0 ; i< d.length; i++){
			var room = ".t" + d[i].roomNum;
			document.querySelector(room).className = "t"+d[i].roomNum;
				document.querySelector(room).style = "background:#77A57E"; 
			}
		}
	})
}
} 

$(function() {
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	// 시작일(date1)은 종료일(date2) 이후 날짜 선택 불가
	// 종료일(date2)은 시작일(date1) 이전 날짜 선택 불가
	// 시작일.
	$('#date1').datepicker(
			{
				dateFormat : "yy-mm-dd",
				monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월",
						"8월", "9월", "10월", "11월", "12월" ],
				dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
				buttonImage : "/jdAdmin/images/calendar.png", // 버튼 이미지
				buttonImageOnly : true, // 버튼 이미지만 표시할지 여부
				buttonText : "날짜선택", // 버튼의 대체 텍스트
				yearRange : 'c0:c+200',
				currentText : '오늘 날짜',
				showButtonPanel : true,
				closeText : '닫기',
				showAnim : "slide",
				dateFormat : "yy-mm-dd", // 날짜의 형식
				showMonthAfterYear : true,
				minDate : 0,
				changeYear : true,
				changeMonth : true, // 월을 이동하기 위한 선택상자 표시여부
				onClose : function(selectedDate) {
					// 시작일(date1) datepicker가 닫힐때
					// 종료일(date2)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
					$("#date2").datepicker("option", 'minDate', selectedDate);
					var date = $(this).datepicker('getDate');
					date.setDate(date.getDate() + 7); // Add 7 days
					$('#date2').datepicker("option", "maxDate", date); // Set
					// as
					// default
				}
			});
	// 종료일
	$('#date2').datepicker(
			{
				dateFormat : "yy-mm-dd",
				monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월",
						"8월", "9월", "10월", "11월", "12월" ],
				dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
				dateFormat : "yy-mm-dd",
				yearRange : 'c0:c+200',
				showMonthAfterYear : true,
				currentText : '오늘 날짜',
				showButtonPanel : true,
				closeText : '닫기',
				changeYear : true,
				changeMonth : true,
				showAnim : "slide",
				maxDate : 0,
				onClose : function(selectedDate) {
					// 종료일(date2) datepicker가 닫힐때
					// 시작일(date1)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정
					$("#date1").datepicker("option", "maxDate", selectedDate);
				}
			});
})