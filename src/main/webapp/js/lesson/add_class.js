/* 页面加载完毕后，初始化chosen下拉框 */
$(function() {
	//加载所有课程
	loadCourses();
	//课程加载完毕，加载所有老师
	loadTeachers();
	//为按钮添加监听
	$("#btnSubmit").click(function (){
		$.ajax({
			url: "lesson/addLesson.do",
			data: $("#formAddLesson").serialize(),
			type:'post',
			success: function(jsonObj){
				if(jsonObj.status==0){
					location="class.html";
				}else{
					alert(jsonObj.msg);
				}
			}
		});
	})
});

/**
 * 加载所有课程
 * @returns
 */
function loadCourses(){
	var url = PROJECTPATH + "/course/findAllCourses.do";
	$.get(url, function(jsonObj) {
		if (jsonObj.status == 0) {
			var list = jsonObj.data;
			$("#selCourseName").empty(); // 清空所有option
			$.each(list, function(index, item) {
				$("#selCourseName").append(
						"<option value='" + item.id + "'>" + item.title + "</option>");
			});
			// 初始化chosen下拉框
			$("#selCourseName").trigger("liszt:updated")
		}
	}, 'json');
}

/**
 * 加载所有讲师
 * @returns
 */
function loadTeachers(){
	var url = PROJECTPATH + "/teacher/queryTeacherNames.do";
	$.get(url, function(jsonObj) {
		if (jsonObj.status == 0) {
			var list = jsonObj.data;
			$("#selTeacherName").empty(); // 清空所有option
			$.each(list, function(index, item) {
				$("#selTeacherName").append(
						"<option value='" + item.id + "'>" + item.name + "</option>");
			});
			// 初始化chosen下拉框
			$("#selTeacherName").trigger("liszt:updated")
		}
	}, 'json');
}


