package com.fenbi.fbms.mapper;

import java.util.List;

import com.fenbi.fbms.entity.Function;

public interface FunctionMapper {
	/**查询全部的功能
	 * @return
	 */
	List<Function> selectAllFunctions();
	/**根据角色id查询该角色下所有的功能
	 * @param roleId 角色id
	 * @return
	 */
	List<Function> selectAllByRoleId(int roleId);
	/**根据用户id查询该用户所有的功能
	 * @param userId用户id
	 * @return 该用户被授予角色下对应的所有功能
	 */
	List<Function> selectAllByUserId(int userId);
}
