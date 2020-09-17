
$(function() {
	
	$('#manu>ul>li>a').css('color', '#FFFFFF');
	$('#manu>ul>li>a').css('font-size', '18px');

	$('#manu>ul>li>a').hover(function() {
		$(this).css('color', '#435F5A');
	}, function() {
		$(this).css('color', '#000000');
	})

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
						.children().children("a").css("color", "#FFFFFF");
				$(this).children().children().eq(1).children().children()
						.children().children("a").css("font-size", "18px");

			})
	$("#room1").hover(function() {
		$(this).parent().parent().parent().children().eq(1).children().children().eq(1).css("background-image",'url("https://gyonewproject.000webhostapp.com/byeolsolResort/healingRoom/img7-3.jpg")');
		$(this).parent().parent().parent().children().eq(1).children().children().eq(0).children().children().eq(0).html("CONCEPT 1");
		$(this).parent().parent().parent().children().eq(1).children().children().eq(0).children().children().eq(2).html("힐링룸 입니다.");
	})
	$("#room2").hover(function() {
		$(this).parent().parent().parent().children().eq(1).children().children().eq(1).css("background-image",'url("https://gyonewproject.000webhostapp.com/byeolsolResort/kidsRoom/img8-1.jpg")');
		$(this).parent().parent().parent().children().eq(1).children().children().eq(0).children().children().eq(0).html("CONCEPT 2");
		$(this).parent().parent().parent().children().eq(1).children().children().eq(0).children().children().eq(2).html("키즈룸 입니다.");
	})
	$("#room3").hover(function() {
		$(this).parent().parent().parent().children().eq(1).children().children().eq(1).css("background-image",'url("https://gyonewproject.000webhostapp.com/byeolsolResort/gameRoom/img9-1.jpg")');
		$(this).parent().parent().parent().children().eq(1).children().children().eq(0).children().children().eq(0).html("CONCEPT 3");
		$(this).parent().parent().parent().children().eq(1).children().children().eq(0).children().children().eq(2).html("게임룸 입니다.");
	})
});
