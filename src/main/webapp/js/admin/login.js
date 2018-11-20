/** 与login.html配套使用 */
$(function(){
	$("#btnSubmit").click(function(){
		var url = "admin/login.do";
		var params = $("#formLogin").serialize();
		$.post(url, params, function(jsonObj){
			if(jsonObj.status==0){
				location="index.html";
			}else{
				alert(jsonObj.msg);
			}
		}, 'json');
	});
	
})