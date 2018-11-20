package com.fenbi.fbms.mapper;

import java.util.List;

import com.fenbi.fbms.entity.Course;
import com.fenbi.fbms.entity.Page;

public interface CourseMapper {
	/**根据查询关键字和页码查询当前页所有的直播课列表
	 * @param page 封装了查询条件和相关参数
	 * @return  直播课列表对象
	 */
	List<Course> selectAll(Page page);
	/**根据关键字查询直播课的总数量
	 * @param name 查询关键字
	 * @return 直播课的总数量
	 */
	int selectCount(String name);
	/**根据直播课id查询当前直播课信息
	 * @param id 直播课id
	 * @return  直播课信息
	 */
	Course selectById(int id);
	/**添加直播课
	 * @param course 封装了直播课信息的对象
	 */
	void insert(Course course);
	/**修改直播课
	 * @param course 封装了直播课信息的对象
	 */
	void update(Course course);
	/**修改课程状态   未上架／上架／下架
	 * @param courseId  课程id
	 * @param status  0表示未来上架，1表示上架，2表示下架
	 */
	void updateStatus(Course course);
	/**查询所有的课程
	 * @return 返回所有的课程名称
	 */
	List<Course> selectCourseNames();
	
}
