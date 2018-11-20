/** 与add_user.html配套使用 */
var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];

$(function(){
	loadAdmin();
	//加载所有角色的列表
	loadRoles();
	//给确认按钮添加监听
	$("#btnSubmit").click(function(){
		//发送请求，为当前用户分配角色
		var url = PROJECTPATH+"/admin/modifyAdminRoles.do";
		var params = $("#formUpdateAdmin").serialize()+"&id="+id;
		$.post(url, params, function(jsonObj){
			if(jsonObj.status==0){
				location="user.html";
			}
		}, 'json');
	});
})

/**
 * 加载角色列表
 */
function loadRoles(){
	var url = PROJECTPATH + "/role/queryRoles.do";
	$.get(url, function(jsonObj){
		if(jsonObj.status==0){
			var list = jsonObj.data; //全部角色列表
			//更新select下拉框下的内容
			$("#selRole").empty();
			$.each(list, function(index, item){
				$("#selRole").append("<option value='"+item.id+"'>"+item.name+"</option>");
			});
			//再发送一次请求，访问当前用户所拥有的角色list
			$.get(PROJECTPATH+"/admin/quaryAdminRoles.do", {"adminId":id}, function(jsonObj){
				if(jsonObj.status==0){
					var roleList = jsonObj.data; //当前用户的角色列表
					//遍历select下拉列表框中的所有option，看一下option的value属性是否是当前用户所拥有的角色id，如果是则设置selected
					var opts = $("#selRole").children();
					$.each(opts, function(index, opt){
						for(j=0; j<roleList.length; j++){
							if(opt.value==roleList[j].id){ //看一下option的value属性与当前用户所拥有的角色id是否一致
								opt.selected=true;
							}
						}
					});
					//更新chosen插件，渲染页面
					$("#selRole").trigger("liszt:updated");
				}
			}, 'json');
		}
	}, 'json');
}


/**
 * 加载当前用户
 */
function loadAdmin(){
	var url = PROJECTPATH + "/admin/queryAdminByAdminId.do";
	$.get(url, {'id':id}, function(jsonObj) {
		if (jsonObj.status == 0) {
			var admin = jsonObj.data;
			$("#spanUsername").html(admin.username);
			$("#spanName").html(admin.name);
			$("#spanEmail").html(admin.email);
			$("spanStatus").html(admin.status==1?'在职':'离职');
		}
	}, 'json');
}
