package com.fenbi.fbms.service;

import java.util.List;

import com.fenbi.fbms.entity.Function;

public interface FunctionService {
	/**查询全部的功能
	 * @return
	 */
	List<Function> queryAllFunctions();
	/**根据角色id查询该角色下所有的功能
	 * @param roleId 角色id
	 * @return
	 */
	List<Function> queryAllByRoleId(int roleId);
	/**根据用户id查询该用户被授予所有角色下全部的功能
	 * @param userId 用户id
	 * @return 该用户被授予所有角色下全部的功能
	 */
	List<Function> queryAllByUserId(int userId);
	
}
