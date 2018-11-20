/** 与add_user.html配套使用 */
var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];

$(function(){
	loadAdmin();
	$("#btnSubmit").click(function(){
		var url = "admin/modifyAdmin.do";
		var params = $("#formUpdateAdmin").serialize()+"&id="+id;
		$.post(url, params, function(jsonObj){
			if(jsonObj.status==0){
				location="user.html";
			}else{
				alert(jsonObj.msg);
			}
		}, 'json');
	});
	
})


/**
 * 加载当前用户
 */
function loadAdmin(){
	var url = PROJECTPATH + "/admin/queryAdminByAdminId.do";
	$.get(url, {'id':id}, function(jsonObj) {
		if (jsonObj.status == 0) {
			var admin = jsonObj.data;
			$("#inputUsername").val(admin.username);
			$("#inputName").val(admin.name);
			$("#inputEmail").val(admin.email);
			//更新两个select标签，而这两个select使用了chosen的js库，所以更得时候需要使用chosen提供的API进行更新。
			$("input[value='"+admin.status+"']").parent("span").addClass("checked");
			$("input[value='"+admin.status+"']").attr("selected",true);
		}
	}, 'json');
}
