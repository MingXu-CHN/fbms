/** 与add_user.html配套使用 */
$(function(){
	$("#btnSubmit").click(function(){
		var url = "admin/addAdmin.do";
		var params = $("#formAddAdmin").serialize();
		$.post(url, params, function(jsonObj){
			if(jsonObj.status==0){
				location="user.html";
			}else{
				alert(jsonObj.msg);
			}
		}, 'json');
	});
	
})