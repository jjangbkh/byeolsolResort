function updateRoom(id) {
	event.preventDefault()
	location.href = "/reserv/updateReserv?reservId="+id;
}

function deleteRoom(id) {
	event.preventDefault()
	location.href = "/reserv/deleteReserv?reservId="+id;
}

$(function() {
	$('#manu>ul>li>a').css('color', '#000000');
	$('#manu>ul>li>a').css('font-size', '18px');

	$('#manu>ul>li>a').hover(function() {
		$(this).css('color', '#435F5A');
	}, function() {
		$(this).css('color', '#000000');
	});

	$('#manu>ul>li>ul>li>a').hover(
			function() {
				$(this).css('color', '#34756A');
				$(this).parent().parent().parent().children('#manu>ul>li>a')
						.css('color', '#435F5A');
			},
			function() {
				$(this).css('color', '#000000');
				$(this).parent().parent().parent().children('#manu>ul>li>a')
						.css('color', '#000000');
			});

	$("header").hover(
			function() {
				$(this).children().children().eq(1).children().children()
						.children().children("a").css("color", "#000000");
			},
			function() {
				$(this).children().children().eq(1).children().children()
						.children().children("a").css("color", "#000000");
				$(this).children().children().eq(1).children().children()
						.children().children("a").css("font-size", "18px");
			})
})
$(function() {
	var endDate = $("input[name=endDate]");
	var startDate = $("input[name=startDate]")
	var roomNum = $("input[name=roomNum]").val();
	var totalP = document.querySelector("input[name=totalPrice]");
	endDate.on("focusout", function() {
		var endDateData = endDate.val();
		var startDateData = startDate.val();
		if (endDateData != "" && startDateData != "") {
			var data = startDateData + "," + endDateData;
			$.ajax({
				url : "/reserv/printTotalPrice",
				type : "post",
				data : {
					"roomNum" : roomNum,
					"startDate" : startDateData,
					"endDate" : endDateData
				},
				success : function(d) {
					totalP.value = d;
				}
			})
		}
	})
})