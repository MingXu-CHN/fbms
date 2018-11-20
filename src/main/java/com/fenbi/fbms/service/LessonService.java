package com.fenbi.fbms.service;

import java.util.List;

import com.fenbi.fbms.entity.Lesson;

public interface LessonService {
	/**添加课时
	 * @param lesson  封装了课时信息的对象
	 */
	void addLesson(Lesson lesson);
	/**根据关键字和页码查询 课时信息列表
	 * @param title 查询关键字
	 * @param currentPage 当前第几页
	 * @return 课时列表
	 */
	List<Lesson> queryLessons(String title,int currentPage);
	/**查询总行数
	 * @param title 查询关键字
	 * @return  返回总行数
	 */
	int queryCount (String title);
	/**根据id查询课时信息
	 * @param id 课时id
	 * @return 封装了课时信息的Lesson对象
	 */
	Lesson queryLessonByLessonId(int id);
	/**修改用户信息 
	 * @param lesson封装了课时信息的Lesson对象
	 */
	void modifyLesson(Lesson lesson);
	/**通过lessonId将课时删除
	 * 
	 * @param lessonId课时id
	 */
	void removeLessonById(int id);
	/**
	 * 
	 * @param courseId 课程Id
	 * @return 课时列表
	 */
	
	List<Lesson> queryByCourseId(int courseId);
	
}
