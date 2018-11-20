/* 添加讲师页面引入的js */
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


$(function(){
	//给确定按钮添加监听，当点击确定时，提交ajax请求，添加讲师信息（讲师信息中包含图片）
	$("#btnSubmit").click(function(){
		//处理表单参数
		var formData = new FormData($("#formAddTeacher")[0]);
		formData.append("description", editor.txt.html());
		$.ajax({
			url:"teacher/addTeacher.do",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			success:function(jsonObj){
				if(jsonObj.status==0){
					location='teachers.html';
				}
			}
		});
	});
})



