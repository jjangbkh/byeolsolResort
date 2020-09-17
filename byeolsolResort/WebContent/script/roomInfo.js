$(function() {
	var clickCnt = 0;
    var list = $("div.img_slide>ul");

    list.append($("div.img_slide>ul>li").eq(0).clone());
    list.append($("div.img_slide>ul>li").eq(1).clone());
    list.append($("div.img_slide>ul>li").eq(2).clone());
    var length = $("div.img_slide>ul>li").length;
    var img_width = $("div.img_slide>ul>li").width();
    $("div.img_slide>ul").css("width",img_width*length + "px");

    $("#next").click(function() {
      if(!list.is(":animated")){
      clickCnt++;
      if (clickCnt == 4) {
        list.css("margin-left","0px");
        clickCnt = 1;
      }
      var marginLeft = clickCnt*img_width;
      list.animate({"margin-left":-marginLeft+"px"}, 500)
      }
    });
    $("#prev").click(function() {
      if(!list.is(":animated")){
      if (clickCnt == 0) {
        list.css("margin-left","-3300px");
        clickCnt = 3;
      }
      clickCnt--;
      var marginLeft = clickCnt*-img_width;
      list.animate({"margin-left":+marginLeft+"px"}, 500);
      }
  });
})
