package com.fenbi.fbms.service;

import java.util.List;

import com.fenbi.fbms.entity.Teacher;
import com.fenbi.fbms.exception.UpdateStatusFailException;

public interface TeacherService {
	/**添加教师
	 * @param teacher  封装了教师信息的对象
	 */
	void save(Teacher teacher);
	
	/**根据关键字和页码查询 教师信息列表
	 * @param key 查询关键字
	 * @param currentPage 当前第几页
	 * @return 教师列表
	 */
	List<Teacher> queryAll(String key,int currentPage);
	
	/**根据id查询教师信息
	 * @param id 教师id
	 * @return 封装了教师信息的Teacher对象
	 */
	Teacher queryById(int id);
	
	/**修改用户信息 
	 * @param teacher封装了教师信息的Teacher对象
	 * @return true表示修改成功，false表示修改失败
	 */
	void modify(Teacher teacher);
	
	/**修改教师的职位状态
	 * @param teahcerId 教师id
	 * @param status 修改后的状态
	 */
	void modifyStatus(int teacherId,int status) throws UpdateStatusFailException;
	/**
	 * 查询总行数
	 * @param key 查询关键字
	 * @return  返回总行数
	 */
	int queryCount (String key);
	
	/**查询所有的老师
	 * @return 返回所有的老师名字
	 */
	List<Teacher> queryTeacherNames();
}
