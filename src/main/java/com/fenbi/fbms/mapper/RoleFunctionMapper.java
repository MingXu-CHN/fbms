package com.fenbi.fbms.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleFunctionMapper {
	/**向角色功能关联关系表中添加一条记录
	 * @param roleId角色id
	 * @param functionId 功能id
	 */
	void insert(@Param("roleId")int roleId,@Param("functionId")int functionId);
	/**根据角色id删除该角色下所有的功能
	 * @param roleId 角色id
	 */
	void deleteAllByRoleId(int roleId);
}
