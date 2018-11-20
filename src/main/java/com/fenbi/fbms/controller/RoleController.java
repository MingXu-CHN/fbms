package com.fenbi.fbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenbi.fbms.entity.FenbiResult;
import com.fenbi.fbms.entity.Function;
import com.fenbi.fbms.entity.Role;
import com.fenbi.fbms.service.FunctionService;
import com.fenbi.fbms.service.RoleService;
/**
 * 角色
 * @author xulei
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private FunctionService functionService;
	@Autowired
	private RoleService roleService;
	/**
	 * 查询所有功能
	 * @return
	 */
	@RequestMapping("/queryAllFunctions.do")
	@ResponseBody
	public FenbiResult queryAllFunctions() {
		List<Function> list = functionService.queryAllFunctions();
		return new FenbiResult(list);
	}
	/**
	 * 添加角色
	 * @param roleName 角色名称
	 * @param functionIds 功能
	 * @return
	 */
	@RequestMapping("/addRole.do")
	@ResponseBody
	public FenbiResult addRole(String roleName,Integer...functionIds) {
		roleService.addRole(roleName,functionIds);
		return new FenbiResult();
	}
	/**根据关键字和页码查询 角色信息列表
	 * @param name 查询关键字/姓名
	 * @param currentPage 当前第几页
	 * @return 角色列表
	 */
	@RequestMapping("/queryRolesList.do")
	@ResponseBody
	public FenbiResult queryRolesList(String name,Integer currentPage) {
		List<Role> roles = roleService.queryRolesList(name, currentPage);
		return new FenbiResult(roles);
	}
	/**
	 * 通过角色ID查询功能
	 * @param roleId 角色ID
	 * @return
	 */
	@RequestMapping("/queryFunctionsByRoleId.do")
	@ResponseBody
	public FenbiResult queryFunctionsByRoleId(Integer roleId) {
		Role role = roleService.queryFunctionsByRoleId(roleId);
		return new FenbiResult(role);
	}
	/**
	 * 通过角色ID修改功能
	 * @param roleId 角色ID
	 * @param functionIds 功能
	 * @return
	 */
	@RequestMapping("/modifyRoleFunctions.do")
	@ResponseBody
	public FenbiResult modifyRoleFunctions(Role role,Integer...functionIds) {
		roleService.modifyRoleFunctions(role, functionIds);
		return new FenbiResult();
	}
	/**
	 * 通过角色ID删除角色
	 * @param roleId 角色ID
	 * @return
	 */
	@RequestMapping("/removeRoleById.do")
	@ResponseBody
	public FenbiResult removeRoleById(Integer roleId) {
		Boolean deleteRole = roleService.deleteRoleById(roleId);
		if(deleteRole) {
			//删除成功
			return new FenbiResult(0);
		}else{
			//该角色与其他管理员有关联，无法删除
			return new FenbiResult(1);
		}
	}
	
	/**根据关键字查询角色列表，得到总行数
	 * 
	 * @param name 姓名
	 * @return
	 */
	@RequestMapping("/queryCount.do")
	@ResponseBody
	public FenbiResult queryCount(String name) {
		int count = roleService.queryCount(name);
		return new FenbiResult(count);
	}
	/**
	 * 查询所有角色
	 * @return
	 */
	@RequestMapping("/queryRoles.do")
	@ResponseBody
	public FenbiResult queryRoles() {
		List<Role> allRoles = roleService.queryRoles();
		return new FenbiResult(allRoles);
	}

}
