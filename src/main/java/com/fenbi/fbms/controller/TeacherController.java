package com.fenbi.fbms.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fenbi.fbms.entity.FenbiResult;
import com.fenbi.fbms.entity.Teacher;
import com.fenbi.fbms.exception.UpdateStatusFailException;
import com.fenbi.fbms.service.TeacherService;

/**
 * 处理讲师模块相关请求
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	TeacherService teacherService;

	@RequestMapping("/addTeacher")
	@ResponseBody
	public FenbiResult addTeacher(Teacher teacher, MultipartFile file, HttpServletRequest request) throws IOException {
		// 1. 把file对象写入文件，并且获取客户端可以访问该图片的url地址，如：/fbms/uploadFiles/xxxxxxxx.jpg
		String path = request.getSession().getServletContext().getRealPath("/uploadfiles");
		// 2.jpg --> .jpg
		String fileName = file.getOriginalFilename();
		String saveFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("."), fileName.length());
		File dir = new File(path, saveFileName);
		if (!dir.getParentFile().exists()) {
			dir.getParentFile().mkdirs();
		}
		// 调用file.transferTo()直接保存文件
		file.transferTo(dir);
		String url = "/fbms/uploadfiles/" + saveFileName;
		// 2. 把url设置为teacher的photoUrl的属性值。
		teacher.setPhotoUrl(url);
		// 3. 整理teacher对象的其他属性：createTime等
		teacher.setCreateTime(new Timestamp(System.currentTimeMillis()));
		// 4. 调用业务层，添加讲师即可。
		teacherService.save(teacher);
		return new FenbiResult();
	}

	/**
	 * 分页查询老师列表
	 * 
	 * @param key
	 *            姓名的关键字
	 * @param currentPage
	 *            当前页
	 * @return 该页的信息
	 */
	@RequestMapping("/queryAllTeacher.do")
	@ResponseBody
	public FenbiResult queryAllTeacher(String keyword, Integer page) {
		List<Teacher> list = teacherService.queryAll(keyword, page);
		return new FenbiResult(list);
	}

	/**
	 * 查询总页数
	 * 
	 * @param 姓名关键字
	 * @return 总页数
	 */
	@RequestMapping("/queryCount.do")
	@ResponseBody
	public FenbiResult queryCount(String keyword) {
		int total = teacherService.queryCount(keyword);
		return new FenbiResult(total);
	}

	/**
	 * 通过id查询老师
	 * 
	 * @param id
	 *            教师Id
	 * @return 老师信息封装的对象
	 */

	@RequestMapping("/queryById.do")
	@ResponseBody
	public FenbiResult queryById(Integer id) {
		Teacher teacher = teacherService.queryById(id);
		return new FenbiResult(teacher);

	}

	/**
	 * 修改老师信息
	 * 
	 * @param teacher
	 *            老师信息封装的对象
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/modify.do")
	@ResponseBody
	public FenbiResult modify(Teacher teacher, MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		// 1. 把file对象写入文件，并且获取客户端可以访问该图片的url地址，如：/fbms/uploadFiles/xxxxxxxx.jpg
		if (!file.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("/uploadfiles");
			// 2.jpg --> .jpg
			String fileName = file.getOriginalFilename();
			String saveFileName = System.currentTimeMillis()
					+ fileName.substring(fileName.indexOf("."), fileName.length());
			File dir = new File(path, saveFileName);
			if (!dir.getParentFile().exists()) {
				dir.getParentFile().mkdirs();
			}
			// 调用file.transferTo()直接保存文件
			file.transferTo(dir);
			String url = "/fbms/uploadfiles/" + saveFileName;
			// 2. 把url设置为teacher的photoUrl的属性值。
			teacher.setPhotoUrl(url);
		}
		// 4. 调用业务层，添加讲师即可。
		teacherService.modify(teacher);
		return new FenbiResult();

	}

	/**
	 * 修改工作状态
	 * 
	 * @param id
	 *            教师ID
	 * @param status
	 *            工作状态码，1为在职，0为离职
	 * @return
	 */
	@RequestMapping("/modifyStatus.do")
	@ResponseBody
	public FenbiResult modifyStatus(Integer id, Integer status) {
		try {
			teacherService.modifyStatus(id, status);
		} catch (UpdateStatusFailException e) {
			return new FenbiResult(FenbiResult.STATUS_FAIL, e.getMessage(), null);
		}
		return new FenbiResult();
	}

	/**
	 * 课时lesson查询在职老师的姓名
	 * 
	 * @return 老师的姓名列表
	 */
	@RequestMapping("/queryTeacherNames.do")
	@ResponseBody
	public FenbiResult queryTeacherNames() {
		List<Teacher> teacherNames = teacherService.queryTeacherNames();
		return new FenbiResult(teacherNames);
	}
}
