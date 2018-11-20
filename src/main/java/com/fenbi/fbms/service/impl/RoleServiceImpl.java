package com.fenbi.fbms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.fbms.entity.Function;
import com.fenbi.fbms.entity.Page;
import com.fenbi.fbms.entity.Role;
import com.fenbi.fbms.mapper.FunctionMapper;
import com.fenbi.fbms.mapper.RoleFunctionMapper;
import com.fenbi.fbms.mapper.RoleMapper;
import com.fenbi.fbms.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleFunctionMapper roleFunctionMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private FunctionMapper functionMapper;
	
	public void addRole(String roleName, Integer[] functionIds) {
		Role role = new Role();
		role.setName(roleName);
		role.setCreateTime(new Timestamp(new Date().getTime()));
		roleMapper.insertRole(role);
		Integer roleId = role.getId();
		if(null != functionIds) {
			for (int i = 0; i < functionIds.length; i++) {
				roleFunctionMapper.insert(roleId, functionIds[i]);
			}
		}
	}

	public List<Role> queryRolesList(String name, int currentPage) {
		Page page = new Page(currentPage);
		page.put("name", name);
		return roleMapper.selectRoleList(page);
	}

	public Role queryFunctionsByRoleId(int id) {
		Role role = roleMapper.selectRoleById(id);
		List<Function> roleFunctions = new ArrayList<Function>();
		roleFunctions = functionMapper.selectAllByRoleId(id);
		role.setRoleFunctions(roleFunctions); 
		return role;
	}

	public void modifyRoleFunctions(Role role,Integer[] functionIds) {
		roleMapper.update(role);
		Integer id = role.getId();
		roleFunctionMapper.deleteAllByRoleId(id);
		if(null != functionIds) {
			for (int i = 0; i < functionIds.length; i++) {
				roleFunctionMapper.insert(id, functionIds[i]);
			}
		}
	}

	public Boolean deleteRoleById(int roleId) {
		//根据roleId查看fb_admin_role表是否有管理员(admin)已经授权使用
		int adminCount = roleMapper.selectAdminCountByRoleId(roleId);
		if(adminCount==0) {
			//adminCount==0表示没有用户与角色关联，可以删除
			//根据roleId删除fb_role_function表的数据
			roleMapper.deleteRoleFunctionsByRoleId(roleId);
			//根据roleId删除fb_role表的数据
			roleMapper.deleteRoleById(roleId);
			return true;
		}else {
			return false;
		}
	}

	public int queryCount(String name) {
        int count = roleMapper.selectCount(name);
        int totalPage;
        if(count%Page.PAGE_SIZE==0) {
            totalPage = count/Page.PAGE_SIZE;
        }else {
            totalPage = count/Page.PAGE_SIZE+1;
        }
        return totalPage;
    }

	public List<Role> queryRoles() {
		return roleMapper.selectRoles();
	}

}
