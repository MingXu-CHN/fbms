package com.fenbi.fbms.service;

import java.util.List;

import com.fenbi.fbms.entity.Admin;
import com.fenbi.fbms.entity.Role;

public interface AdminService {
	/**登录
	 * @param username 用户名
	 * @param password 密码
	 * @return  登录成功返回Admin对象，失败返回null
	 */
	Admin login(String username,String password);
	/**检查用户名是否可用
	 * @param username 用户名
	 * @return true表示可以使用，false表示不能使用（已经存在）
	 */
	boolean checkName(String username);
	/**添加管理员工
	 * @param admin  封装了管理员信息的对象
	 */
	void add(Admin admin);
	
	/**根据关键字和页码查询 管理员信息列表
	 * @param name 查询关键字
	 * @param currentPage 当前第几页
	 * @return 管理员列表
	 */
	List<Admin> queryAdminsList(String name,int currentPage);
	/**根据id查询管理员信息
	 * @param id 管理员id
	 * @return 封装了管理员信息的Admin对象
	 */
	Admin queryById(int id);
	/**修改用户信息 
	 * @param admin封装了管理员信息的Admin对象
	 * @return true表示修改成功，false表示修改失败
	 */
	void modify(Admin admin);
	/**根据id删除当前管理员
	 * @param id 管理员id
	 * @return true表示删除成功，false表示删除失败
	 */
	void removeAdminById(int id);
	/**查询总行数
	 * @param name
	 * @return
	 */
	int queryCount(String name);
	/**修改用户和角色的关系对应表
	 * @param adminId 用户ID
	 * @param roleIds 用户ID对应的角色ID
	 */
	void modifyAdminRoles(int adminId,Integer[] roleIds);
	/**通过用户ID查询对应的角色
	 * @param adminId 用户ID
	 * @return 角色
	 */
	List<Role> quaryAdminRoles(int adminId);
	/**检查用户名是否可用
	 * @param username 用户名
	 * @return
	 */
	Boolean checkUsername(String username);
}
