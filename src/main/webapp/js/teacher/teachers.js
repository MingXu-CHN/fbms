/* 讲师列表页面引入的js */

$(function(){
	//发送请求，加载课程列表
	loadTeachers(1);
	loadPages();
	//给搜索的文本框添加监听
	$("#inputSearch").on('change', function(){
		//加载课程列表
		loadTeachers(1);
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
	var url = PROJECTPATH+"/teacher/queryCount.do";
	$.post(url, params, function(jsonObj){
		if(jsonObj.status==0){
			totalCounts = jsonObj.data;
			//生成分页器
		    $.jqPaginator('#paginator', {
		        totalCounts: totalCounts,
		        pageSize:5,
		        visiblePages: 5,
		        currentPage: 1,
		        first: '<li class="prev"><a href="javascript:;">首页</a></li>',
		        last: '<li class="prev"><a href="javascript:;">尾页</a></li>',
		        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
		        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
		        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
		        onPageChange: function (num, type) { 
		        	//当点击分页器中的页码li时，将会执行该函数，并且把num页码回调回来
		        	loadTeachers(num);
		        }
		    });
		}
	}, 'json');
}

/* 加载课程列表 */
function loadTeachers(num){
	//获取关键字
	var url = PROJECTPATH+"/teacher/queryAllTeacher.do";
	var keyword = $("#inputSearch").val();
	var params = {'keyword': keyword , 'page': num}; //发请求时需要传递的参数
	$.post(url, params, function(jsonObj){
		//解析返回的json字符串，更新tbody内的内容
		if(jsonObj.status==0){//成功
			var list = jsonObj.data;
			$("#tbody").empty(); //把tbody的原始内容清空
			$.each(list, function(index, item){
				var tr = "<tr class='odd'>" +
					" <td class='center' width='50px'>" + item.id + " </td> " +
					" <td class='center' width='100px'>" + item.name + " </td> " +
					" <td class='center' width='150px'>" + item.email + " </td> " +
					" <td class='center' width='50px'>" + (item.status==1?'在职':'离职') + " </td> " +
					" <td class='center' width='100px'> " +
					" <a class='btn btn-success' href='modify_teacher.html?id=" + item.id + "'>修改" +
					" </a> " +
					" <a class='btn btn-info' href='javascript:;' onclick='updateStatus("+item.id+", 1)'>入职" +
					" </a> " +
					" <a class='btn btn-danger' href='javascript:;' onclick='updateStatus("+item.id+", 2)'>离职" +
					" </a> " +
					" </td> " +
					" </tr> ";
				$("#tbody").append(tr);
			});
			
		}else{//列表加载失败
			
		}
	}, 'json');
}

/**
 * 更新课程状态
 * @returns
 */
function updateStatus(id, status){
	var url = PROJECTPATH+"/teacher/modifyStatus.do";
	var params = {"id":id, "status":status};
	$.post(url, params, function(jsonObj){
		if(jsonObj.status==0){//修改成功
			//重新加载列表
			location.reload();
		}else{
			alert(jsonObj.msg);
		}
	}, 'json');
}






