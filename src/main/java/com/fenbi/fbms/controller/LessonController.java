package com.fenbi.fbms.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenbi.fbms.entity.FenbiResult;
import com.fenbi.fbms.entity.Lesson;
import com.fenbi.fbms.service.LessonService;
import com.fenbi.fbms.utils.DateUtils;
/**
 * 课程
 * @author xulei
 *
 */
@Controller
@RequestMapping("/lesson")
public class LessonController {

	@Autowired
	private LessonService lessonService;
	/**
	 * 根据课时名称和页码查询课程
	 * @param title 课时名称
	 * @param currentPage 当前第几页
	 * @return
	 */
	@RequestMapping("/queryLessons.do")
	@ResponseBody
	public FenbiResult queryLessons(String keyword, int page) {
		List<Lesson> lessons = lessonService.queryLessons(keyword, page);
		return new FenbiResult(lessons);
	}
	/**
	 * 添加课时
	 * @param lesson 课程
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return
	 */
	@RequestMapping("/addLesson.do")
	@ResponseBody
	public FenbiResult addLesson(Lesson lesson,String startTimeString, String endTimeString) {
		lesson.setStartTime(new Timestamp(DateUtils.stringToDate(startTimeString).getTime()));
		lesson.setEndTime(new Timestamp(DateUtils.stringToDate(endTimeString).getTime()));
		lesson.setCreateTime(new Timestamp(new Date().getTime()));
		lessonService.addLesson(lesson);
		return new FenbiResult();
	}
	/**
	 * 根据课时名称查询数量
	 * @param title 课时名称
	 * @return
	 */
	@RequestMapping("/queryCount.do")
	@ResponseBody
	public FenbiResult queryCount(String keyword) {
		int count = lessonService.queryCount(keyword);
		return new FenbiResult(count);
	}
	/**
	 * 通过课时ID查询该课时
	 * @param lessonId 课时ID
	 * @return
	 */
	@RequestMapping("/queryLessonByLessonId.do")
	@ResponseBody
	public FenbiResult queryLessonByLessonId(Integer id) {
		Lesson lesson = lessonService.queryLessonByLessonId(id);
		return new FenbiResult(lesson);
	}
	/**
	 * 修改课时
	 * @param lesson 课程
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return
	 */
	@RequestMapping("/modifyLesson.do")
	@ResponseBody
	public FenbiResult modifyLesson(Lesson lesson,String startTimeString,String endTimeString) {
		lesson.setStartTime(new Timestamp(DateUtils.stringToDate(startTimeString).getTime()));
		lesson.setEndTime(new Timestamp(DateUtils.stringToDate(endTimeString).getTime()));
		lessonService.modifyLesson(lesson);
		return new FenbiResult();
	}
	/**
	 * 通过课时ID删除该课时
	 * @param lessonId 课时ID
	 * @return
	 */
	@RequestMapping("/removeLessonById.do")
	@ResponseBody
	public FenbiResult removeLessonById(Integer id) {
		lessonService.removeLessonById(id);
		return new FenbiResult();
	}
	/**
	 * 通过直播课ID查询课时
	 * @param courseId 直播课ID
	 * @return
	 */
	@RequestMapping("/queryByCourseId.do")
        @ResponseBody
        public FenbiResult queryByCourseId(Integer courseId) {
	    List<Lesson> list=lessonService.queryByCourseId(courseId);
            return new FenbiResult(list);
	    
	}
}
