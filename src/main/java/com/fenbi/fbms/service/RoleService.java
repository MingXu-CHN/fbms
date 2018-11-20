package com.fenbi.fbms.service;

import java.util.List;

import com.fenbi.fbms.entity.Role;

public interface RoleService {

	/**添加角色
	 * @param roleName 角色名称
	 * @param funct 角色功能ID
	 */
	void addRole(String roleName,Integer[] functionIds);
	
	/**根据关键字和页码查询 角色信息列表
	 * @param name 查询关键字
	 * @param currentPage 当前第几页
	 * @return 角色列表
	 */
	List<Role> queryRolesList(String name,int currentPage);
	/**根据id查询角色信息
	 * @param id 角色id
	 * @return 封装了角色信息的Role对象
	 */
	Role queryFunctionsByRoleId(int id);
	/**修改用户信息 
	 * @param role 角色
	 * @param functionIds功能的id
	 */
	void modifyRoleFunctions(Role role,Integer[] functionIds);
	/**根据id删除当前角色  
	 * @param id 角色id
	 * @return 是否删除成功
	 */
	Boolean deleteRoleById(int id);
	/**查询总行数
	 * @param name
	 * @return
	 */
	int queryCount(String name);
	/**
	 * 查询所有的角色
	 */
	List<Role> queryRoles();
}
