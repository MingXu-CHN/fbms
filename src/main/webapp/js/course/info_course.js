/* 配套info_course.html */
$(function(){
	var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
	var id = (href.split("?")[1]).split("=")[1];
	
	//发送请求，加载课程列表
	loadCourse(id);
	loadLessonsByCourseId(id);
});

/* 加载当前课程信息 */
function loadCourse(id){
	var url = "course/findById.do";
	var params = {"id": id};
	$.get(url, params, function(jsonObj){
		if(jsonObj.status==0){
			var course = jsonObj.data;
			$("#hCourseTitle").html(course.title);
		}
	}, 'json');
}

/* 通过课程id加载所有课时信息 */
function loadLessonsByCourseId(id){
	var url = "lesson/queryByCourseId.do";
	var params = {'courseId':id};
	$.get(url, params, function(jsonObj){
		if(jsonObj.status==0){
			var list = jsonObj.data;
			$("#tbody").empty();
			$.each(list, function(index, item){
				$("#tbody").append('<tr>'+
						'<td class="center">'+item.title+'</td>'+
						'<td class="center">'+item.teacherName+'</td>'+
						'<td class="center">'+item.startTimeString+' - '+item.endTimeString+'</td>'+
					'</tr>');
			});
		}
	}, 'json');
}





