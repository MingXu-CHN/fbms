/* 用户相关js脚本文件 */

$(function(){
	//发送请求，加载用户列表
	loadAdmins(1);
	loadPages();
	//给搜索的文本框添加监听
	$("#inputSearch").on('change', function(){
		//加载用户列表
		loadAdmins(1);
		loadPages();
	});
})

/**
 * 加载分页器
 * @returns
 */
function loadPages(){
	//初始化分页器
	var totalCounts;
	var keyword = $("#inputSearch").val();
	var params = {'keyword': keyword}; //发请求时需要传递的参数
	var url = PROJECTPATH+"/admin/queryCount.do";
	$.post(url, params, function(jsonObj){
		if(jsonObj.status==0){
			totalPages = jsonObj.data;
			//生成分页器
		    $.jqPaginator('#paginator', {
		        totalPages: totalPages,
		        visiblePages: 5,
		        currentPage: 1,
		        first: '<li class="prev"><a href="javascript:;">首页</a></li>',
		        last: '<li class="prev"><a href="javascript:;">尾页</a></li>',
		        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
		        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
		        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
		        onPageChange: function (num, type) { 
		        	//当点击分页器中的页码li时，将会执行该函数，并且把num页码回调回来
		        	loadAdmins(num);
		        }
		    });
		}
	}, 'json');
}

/* 加载用户列表 */
function loadAdmins(num){
	//获取关键字
	var url = PROJECTPATH+"/admin/queryAdminsList.do";
	var keyword = $("#inputSearch").val();
	var params = {'keyword': keyword , 'page': num}; //发请求时需要传递的参数
	$.post(url, params, function(jsonObj){
		//解析返回的json字符串，更新tbody内的内容
		if(jsonObj.status==0){//成功
			var list = jsonObj.data;
			$("#tbody").empty(); //把tbody的原始内容清空
			$.each(list, function(index, item){
				var html = '<tr id='+item.id+'>\
								<td>'+ item.username +'</td>\
								<td>'+ item.name +'</td>\
								<td>'+ item.email +'</td>\
								<td>'+ (item.status==1?"在职":"离职") +'</td>\
								<td width="180px">\
									<a class="btn btn-success" href="modify_user.html?adminId='+item.id+'">\
										<span>修改</span>\
									</a>\
									<a class="btn btn-info" href="fuction_user.html?adminId='+item.id+'">\
										<span>功能</span>\
									</a>\
									<a class="btn btn-danger" href="javascript:removeAdminById('+item.id+');">\
										<span>删除</span>\
									</a>\
								</td>\
							</tr>';
				$("#tbody").append(html);
			});
		}else{//列表加载失败
			
		}
	}, 'json');
}

/**
 * 更新用户状态
 * @returns
 */
function removeAdminById(id){
	var url = PROJECTPATH+"/admin/removeAdminById.do";
	var params = {"id":id};
	$.post(url, params, function(jsonObj){
		if(jsonObj.status==0){//修改成功
			//重新加载列表
			location.reload();
		}else{
			alert(jsonObj.msg);
		}
	}, 'json');
}


