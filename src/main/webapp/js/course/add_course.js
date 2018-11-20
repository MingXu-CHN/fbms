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

/* 添加课程页面中所需要的js脚本 */
$(function(){
	//页面加载完毕后为按钮添加监听
	$("#btnSubmit").click(function(){
		//使用FormData对象构建表单数据集，用于向服务端传参
		var desc = editor.txt.html();
		var formData = new FormData($("#formAddCourse")[0]);
		formData.append("description", desc);
		$.ajax({
			url:PROJECTPATH+"/course/addCourse.do",
			type:'post',
			data:formData, // "id=asdasd&s=000&name=1233"
			processData:false,
			contentType:false,
			success:function(jsonObj){  
				//jsonObj:  {status:0, msg:'', data:响应数据 }
				if(jsonObj.status==0){ //成功
					window.location=PROJECTPATH+"/course.html";
				}else{ //失败
					alert("失败："+jsonObj.msg);
				}
			}
		})
	});
	$("#btnCancel").click(function(){
		window.location=PROJECTPATH+"/course.html";
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
		}
	}, 'json');
	
	
});

