$(function() {
	var btn = $("button[name=mailCertification]");
	btn.on("click",function(){
	var data = $("input[name=userEmail]").val();
		$.ajax({
			url:"/cus/mailSend",
			type:"post",
			data: {"userEmail" : data},
			success : function(d){
				alert(d);
			}
		})		
	})	
})

