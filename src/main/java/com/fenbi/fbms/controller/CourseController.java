package com.fenbi.fbms.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fenbi.fbms.entity.Course;
import com.fenbi.fbms.entity.FenbiResult;
import com.fenbi.fbms.exception.UpdateStatusFailException;
import com.fenbi.fbms.service.CourseService;
import com.fenbi.fbms.utils.DateUtils;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/updateCourse")
	@ResponseBody
	public FenbiResult updateCourse(Course course, String startTimeString, String endTimeString) {
		course.setStartTime(new Timestamp(DateUtils.stringToDate(startTimeString).getTime()));
		course.setEndTime(new Timestamp(DateUtils.stringToDate(endTimeString).getTime()));
		courseService.update(course);
		return new FenbiResult(course);
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public FenbiResult findById(int id) {
		Course course = courseService.findById(id);
		return new FenbiResult(course);
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public FenbiResult updateStatus(int id, int status) {
		try {
			courseService.updateStatus(id, status);
		} catch (UpdateStatusFailException e) {
			return new FenbiResult(FenbiResult.STATUS_FAIL, e.getMessage(), null);
		}
		return new FenbiResult();
	}
	
	/**
	 * 查询课程总数量
	 */
	@RequestMapping("/countAll")
	@ResponseBody
	public FenbiResult countAll(String keyword) {
		int count=courseService.countAll(keyword);
		return new FenbiResult(count);
	}
	

	/**
	 * 查询课程列表业务
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public FenbiResult findAll(String keyword, @RequestParam(defaultValue="1") int page) {
		List<Course> courses=courseService.findAll(keyword, page);
		return new FenbiResult(courses);
	}
	
	/**
	 * 查询课程列表业务
	 */
	@RequestMapping("/findAllCourses")
	@ResponseBody
	public FenbiResult findAllCourses() {
		List<Course> courses=courseService.findAllCourses();
		return new FenbiResult(courses);
	}
	
	
	/**
	 * 添加课程服务接口
	 * @param course  待添加的课程对象
	 * @param startTimeString  开始时间     格式如下：2018/09/11 10:05  
	 * @param endTimeString   结束时间  格式如下：2018/09/11 10:05
	 * @return
	 */
	@RequestMapping("/addCourse")
	@ResponseBody
	public FenbiResult addCourse(Course course, String startTimeString, String endTimeString) {
		//整理course对象的属性信息
		course.setCreateTime(new Timestamp(System.currentTimeMillis()));
		course.setStartTime(new Timestamp(DateUtils.stringToDate(startTimeString).getTime()));
		course.setEndTime(new Timestamp(DateUtils.stringToDate(endTimeString).getTime()));
		course.setStatus(0);
		//调用service添加课程
		courseService.save(course);
		return new FenbiResult();
	}
	

	/**
	 * 接收上传的图片
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
    @RequestMapping("/upload.do")
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request)
            throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/uploadfiles");
        //  2.jpg  -->  .jpg
        String fileName = file.getOriginalFilename();
        String saveFileName = System.currentTimeMillis()+ fileName.substring(fileName.indexOf("."),fileName.length());
        File dir = new File(path, saveFileName);
        if (!dir.getParentFile().exists()) {
            dir.getParentFile().mkdirs();
        }
        //调用file.transferTo()直接保存文件
        file.transferTo(dir);
        String url = "/fbms/uploadfiles/" + saveFileName;
        String result = "{\"errno\":0,\"data\":[\"" + url + "\"]}";
        return result;
    }
	
}











