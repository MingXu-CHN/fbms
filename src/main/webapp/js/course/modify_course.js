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

/* 修改课程页面中所需要的js脚本 */
$(function(){
	//为按钮添加监听
	$("#btnSubmit").click(function(){
		//收集数据，发送修改课程请求
		var formData = new FormData($("#formUpdateCourse")[0]);
		formData.append("id",id);
		formData.append("description", editor.txt.html());
		$.ajax({
			url: 'course/updateCourse.do',
			type:'post',
			data: formData,
			processData:false,
			contentType:false,
			success : function(jsonObj){
				if(jsonObj.status==0){ //修改成功 跳转页面
					location="course.html";
				}
			}
		});
		
	});
	
	$("#btnCancel").click(function(){
		location="course.html";
	});
	
	
	//加载课程类别列表
	var url = PROJECTPATH+"/courseType/queryAll.do";
	$.get(url, function(jsonObj){
		if(jsonObj.status==0){
			$("#selCourseType").empty();
			var list = jsonObj.data;
			$.each(list, function(index, item){
				$("#selCourseType").append('<option value="'+item.id+'">'+item.name+'</option>');
			});
			//加载当前课程并更新页面
			loadCourse();
		}
	}, 'json');
});

/**
 * 加载课程并更新页面
 * @returns
 */
function loadCourse(){
	//通过id，想服务端发请求，获取课程的详情信息
	var url = PROJECTPATH+"/course/findById.do";
	var params = {"id" : id};
	//加载课程详情信息
	$.post(url, params, function(jsonObj){
		if(jsonObj.status==0){ //服务端已经找到对应的课程对象  更新页面即可
			var course = jsonObj.data;
			//为页面每个控件赋值
			$("#inputTitle").val(course.title);
			editor.txt.html(course.description);
			//为下拉列表设置当前选中项
			$("#selCourseType").val(course.typeId);
			$("#inputPrice").val(course.price);
			$("#inputStartTime").val(course.startTimeString);
			$("#inputEndTime").val(course.endTimeString);
		}
	}, 'json');
	
}



















