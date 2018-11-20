package com.fenbi.fbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.fbms.entity.Course;
import com.fenbi.fbms.entity.Page;
import com.fenbi.fbms.exception.UpdateStatusFailException;
import com.fenbi.fbms.mapper.CourseMapper;
import com.fenbi.fbms.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public void save(Course course) {
		courseMapper.insert(course);
	}

	@Override
	public List<Course> findAll(String keyword, int pageNum) {
		Page page = new Page(pageNum);
		page.put("title", keyword);
		return courseMapper.selectAll(page);
	}

	@Override
	public int countAll(String keyword) {
		return courseMapper.selectCount(keyword);
	}

	@Override
	public void updateStatus(int id, int status) throws UpdateStatusFailException{
		Course course = courseMapper.selectById(id);
		if(status==course.getStatus()) {//不需要修改，因为修改的状态值与当前状态值一致
			throw new UpdateStatusFailException("当前课程状态不需要修改");
		}
		course.setStatus(status);
		courseMapper.updateStatus(course);
	}

	@Override
	public Course findById(int id) {
		return courseMapper.selectById(id);
	}

	@Override
	public void update(Course course) {
		courseMapper.update(course);
	}

	@Override
	public List<Course> findAllCourses() {
		return courseMapper.selectCourseNames();
	}

}
