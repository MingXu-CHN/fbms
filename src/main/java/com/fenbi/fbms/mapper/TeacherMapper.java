package com.fenbi.fbms.mapper;

import java.util.List;

import com.fenbi.fbms.entity.Page;
import com.fenbi.fbms.entity.Teacher;

public interface TeacherMapper {
	/**根据查询关键字和页码查询当前页所有的老师列表
	 * @param key  查询关键字
	 * @param begin 从哪一行开始查询
	 * @return  老师列表对象
	 */
	List<Teacher> selectAll(Page page);
	/**根据关键字查询老师的总数量
	 * @param key查询关键字
	 * @return 老师的总数量
	 */
	int selectCount(String key);
	/**根据老师id查询当前老师信息
	 * @param id 老师id
	 * @return  老师信息
	 */
	Teacher selectById(int id);
	/**添加老师
	 * @param Teacher 封装了老师信息的对象
	 */
	void insert(Teacher teacher);
	/**修改老师
	 * @param Teacher 封装了老师信息的对象
	 */
	void update(Teacher teacher);
	/**根据老师id删除对应的记录
	 * @param id 老师id
	 */
	//void deleteById(int id);
	/**修改教师的职位状态
	 * @param teahcerId 教师id
	 * @param status 修改后的状态
	 */
	void updateStatus(int teacherId,int status);
	/**查询所有的老师
	 * @return 返回所有的老师名字
	 */
	List<Teacher> selectTeacherNames();
	
}
