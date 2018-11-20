var E = window.wangEditor
var editor = new E('#div1', '#div2') // 两个参数也可以传入 elem 对象，class 选择器，此前参数为tools栏和文本框栏
editor.customConfig.showLinkImg = false;
editor.customConfig.uploadImgServer = PROJECTPATH + "/course/upload.do";
editor.customConfig.uploadImgMaxLength = 5;
editor.customConfig.uploadFileName = 'file';
editor.customConfig.uploadImgHeaders = {
	'Accept': 'multipart/form-data'
};
editor.create()

var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];

/* 修改讲师页面中所需要的js脚本 */
$(function(){
	//加载讲师
	loadTeacher();
	
	//为按钮添加监听
	$("#btnSubmit").click(function(){
		//收集数据，发送修改讲师请求
		var formData = new FormData($("#formUpdateTeacher")[0]);
		formData.append("id",id);
		formData.append("description", editor.txt.html());
		$.ajax({
			url: 'teacher/modify.do',
			type:'post',
			data: formData,
			processData:false,
			contentType:false,
			success : function(jsonObj){
				if(jsonObj.status==0){ //修改成功 跳转页面
					location="teachers.html";
				}
			}
		});
		
	});
	
	$("#btnCancel").click(function(){
		location="teachers.html";
	});
	
});

/**
 * 加载讲师并更新页面
 * @returns
 */
function loadTeacher(){
	//通过id，想服务端发请求，获取讲师的详情信息
	var url = PROJECTPATH+"/teacher/queryById.do";
	var params = {"id" : id};
	//加载讲师详情信息
	$.post(url, params, function(jsonObj){
		if(jsonObj.status==0){ //服务端已经找到对应的讲师对象  更新页面即可
			var teacher = jsonObj.data;
			//为页面每个控件赋值
			$("#inputName").val(teacher.name);
			editor.txt.html(teacher.description);
			//为下拉列表设置当前选中项
			$("input[value='"+teacher.gender+"']").parent("span").addClass("checked");
			$("input[value='"+teacher.gender+"']").attr("selected",true);
			$("#inputEmail").val(teacher.email);
			$("#inputUsername").val(teacher.username);
			$("#avatar_preview").attr('src',teacher.photoUrl);
		}
	}, 'json');
	
}



















