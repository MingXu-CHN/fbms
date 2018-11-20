package com.fenbi.fbms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fenbi.fbms.entity.Admin;
import com.fenbi.fbms.entity.Page;
import com.fenbi.fbms.entity.Role;

public interface AdminMapper {
	/**根据用户名查询管理员信息
	 * @param username 用户名
	 * @return 管理员信息
	 */
	Admin selectByUserName(String username);
	/**根据查询关键字和页码查询当前页所有的角色列表
	 * @param page 封装了查询条件和相关参数
	 * @return  角色列表对象
	 */
	List<Admin> selectAll(Page page);
	/**根据关键字查询管理员的总数量
	 * @param key查询关键字
	 * @return 管理员的总数量
	 */
	int selectCount(String name);
	/**根据管理员id查询当前管理员信息
	 * @param id 管理员id
	 * @return  管理员信息
	 */
	Admin selectById(int id);
	/**添加管理员
	 * @param admin 封装了管理员信息的对象
	 */
	void insert(Admin admin);
	/**修改管理员
	 * @param admin 封装了管理员信息的对象
	 */
	void update(Admin admin);
	/**根据管理员id删除对应的记录
	 * @param id 管理员id
	 */
	void deleteById(int adminId);
	/**根据管理员Id删除管理员和角色表(fb_admin_role)关系对应的数据
	 * @param adminId 管理员Id
	 */
	void deleteAdminRoles(int adminId);
	/**修改用户和角色的关系对应表
	 * 
	 */
	void insertAdminRoles(@Param("adminId")int adminId, @Param("roleId")int roleId);
	/**通过用户ID查询对应的角色
	 * @param adminId 用户ID
	 * @return 角色
	 */
	List<Role> selectAdminRoles(int adminId);
	/**检查用户名是否可用
	 * @param username 用户名
	 * @return
	 */
	int checkUsername(@Param("username")String username);
	
}
