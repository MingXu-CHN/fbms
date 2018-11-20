var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];

/* 页面加载完毕后，初始化chosen下拉框 */
$(function() {
	//加载所有课时
	loadCourses();
	//为按钮添加监听
	$("#btnSubmit").click(function (){
		$.ajax({
			url: "lesson/modifyLesson.do",
			data: $("#formUpdateLesson").serialize()+"&id="+id,
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
 * 加载所有课时
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
			//课时加载完毕，加载所有老师
			loadTeachers();
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
			//加载当前课时
			loadLesson();
		}
	}, 'json');
}

/**
 * 加载当前课时
 */
function loadLesson(){
	var url = PROJECTPATH + "/lesson/queryLessonByLessonId.do";
	$.get(url, {'id':id}, function(jsonObj) {
		if (jsonObj.status == 0) {
			var lesson = jsonObj.data;
			$("#inputTitle").val(lesson.title);
			$("#inputStartTime").val(lesson.startTimeString);
			$("#inputEndTime").val(lesson.endTimeString);
			//更新两个select标签，而这两个select使用了chosen的js库，所以更得时候需要使用chosen提供的API进行更新。
			$("#selCourseName").val(lesson.courseId);
			$("#selCourseName").trigger("liszt:updated");
			$("#selTeacherName").val(lesson.teacherId);
			$("#selTeacherName").trigger("liszt:updated");
		}
	}, 'json');
}

