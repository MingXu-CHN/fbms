package com.fenbi.fbms.mapper;

import java.util.List;

import com.fenbi.fbms.entity.Page;
import com.fenbi.fbms.entity.Role;

public interface RoleMapper {
	/**根据查询关键字和页码查询当前页所有的角色列表
	 * @param page 封装了查询条件和相关参数
	 * @return  角色列表对象
	 */
	List<Role> selectRoleList(Page page);
	/**根据关键字查询角色的总数量
	 * @param name 查询关键字
	 * @return 角色的总数量
	 */
	int selectCount(String name);
	/**根据角色id查询当前角色信息
	 * @param id 角色id
	 * @return  角色信息
	 */
	Role selectRoleById(int id);
	/**添加角色
	 * @param role 封装了角色信息的对象
	 * @return 返回插入角色后的主键
	 */
	void insertRole(Role role);
	/**根据角色id删除fb_role表的记录
	 * @param id 角色id
	 */
	void deleteRoleById(int id);
	/**根据角色id删除fb_role_function表的记录
	 * @param id 角色id
	 */
	void deleteRoleFunctionsByRoleId(int id);
	/**查询所有的角色
	 */
	List<Role> selectRoles();
	/**根据roleId查看fb_admin_role表是否有管理员(admin)已经授权使用
	 * @param roleId 角色id
	 * @return 被授予该角色的用户数量
	 */
	int selectAdminCountByRoleId(int roleId);
	/**根据roleId更改角色名称
	 * @param role 封装了角色信息对象
	 */
	void update(Role role);
}
