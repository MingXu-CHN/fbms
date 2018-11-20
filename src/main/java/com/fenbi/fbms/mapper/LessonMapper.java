package com.fenbi.fbms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fenbi.fbms.entity.Lesson;
import com.fenbi.fbms.entity.Page;

public interface LessonMapper {
	/**根据课程id查询当前课程下所有的课时
	 * @param courseId：课程id
	 * @return  当前课程下所有的课时集合
	 */
	List<Lesson> selectByCourseId(int courseId);
	/**根据查询关键字和页码查询当前页所有的课时列表
	 * @param page 封装了查询条件和相关参数
	 * @return  课时列表对象
	 */
	List<Lesson> select(Page page);
	/**根据关键字查询课时的总数量
	 * @param title 查询关键字
	 * @return 课时的总数量
	 */
	int selectCount(@Param("title")String title);
	/**根据课时id查询当前课时信息
	 * @param id 课时id
	 * @return  课时信息
	 */
	Lesson selectById(int id);
	/**添加课时
	 * @param lesson 封装了课时信息的对象
	 */
	void insertLesson(Lesson lesson);
	/**修改课时
	 * @param lesson 封装了课时信息的对象
	 */
	void updateLesson(Lesson lesson);
	/**根据课时id删除对应的记录
	 * @param id 课时id
	 */
	void deleteById(int id);
	/**通过lesson表的courseId查询course表的课程名称
	 * 
	 * @param courseId lesson表的courseId
	 * @return course表的课程名称
	 */
	//String selectCourseName(int courseId);
	/**通过lesson表的teacherId查询teacher表的老师
	 * 
	 * @param teacherId lesson表的teacherId
	 * @return teacher表的老师
	 */
	//String selectTeacherName(int teacherId);
	
}
