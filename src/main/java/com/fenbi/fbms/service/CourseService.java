package com.fenbi.fbms.service;

import java.util.List;

import com.fenbi.fbms.entity.Course;
import com.fenbi.fbms.exception.UpdateStatusFailException;

/**
 * 课程相关业务的抽象接口
 * @author durh
 */
public interface CourseService {
	
	/**
	 * 保存课程信息
	 * @param course
	 */
	public void save(Course course);

	/**
	 * 查询课程列表
	 * @param keyword 关键字title
	 * @param page 当前页码
	 * @return
	 */
	public List<Course> findAll(String keyword, int page);

	/**
	 * 查询课程数量
	 * @return
	 */
	public int countAll(String keyword);

	/**
	 * 通过id，修改课程的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id, int status) throws UpdateStatusFailException;

	/**
	 * 通过Id查询课程信息
	 * @param id
	 * @return
	 */
	public Course findById(int id);

	/**
	 * 修改课程业务
	 * @param course
	 */
	public void update(Course course);

	/**
	 * 查询所有课程
	 * @return
	 */
	public List<Course> findAllCourses();
	
}
