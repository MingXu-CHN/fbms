package com.fenbi.fbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.fbms.entity.Admin;
import com.fenbi.fbms.entity.Page;
import com.fenbi.fbms.entity.Role;
import com.fenbi.fbms.mapper.AdminMapper;
import com.fenbi.fbms.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

	public Admin login(String username, String password) {
		Admin admin = adminMapper.selectByUserName(username);
		if(admin!=null&&password.equals(admin.getPassword())) {
			return admin;
		}
		return null;
	}

	public boolean checkName(String username) {
		Admin admin = adminMapper.selectByUserName(username);
		if(null == admin)
			return true;
		else
			return false;
	}

	public void add(Admin admin) {
		adminMapper.insert(admin);
	}
	
	/**根据关键字和页码查询 管理员信息列表
	 * @param name 查询关键字
	 * @param currentPage 当前第几页
	 * @return 管理员列表
	 */
	public List<Admin> queryAdminsList(String name, int currentPage) {
		Page page = new Page(currentPage);
		page.put("name", name);
		return adminMapper.selectAll(page);
	}
	
	public Admin queryById(int id) {
		return adminMapper.selectById(id);
	}

	public void modify(Admin admin) {
		adminMapper.update(admin);
	}

	public void removeAdminById(int adminId) {
		adminMapper.deleteById(adminId);
		adminMapper.deleteAdminRoles(adminId);
	}

	public int queryCount(String name) {
		int count = adminMapper.selectCount(name);
        int totalPage;
        if(count%Page.PAGE_SIZE==0) {
            totalPage = count/Page.PAGE_SIZE;
        }else {
            totalPage = count/Page.PAGE_SIZE+1;
        }
        return totalPage;
	}

	public void modifyAdminRoles(int adminId, Integer[] roleIds) {
		adminMapper.deleteAdminRoles(adminId);
		if(null != roleIds) {
			for (int i = 0; i < roleIds.length; i++) {
				adminMapper.insertAdminRoles(adminId, roleIds[i]);
			}
		}
	}

	public List<Role> quaryAdminRoles(int adminId) {
		return adminMapper.selectAdminRoles(adminId);
	}

	public Boolean checkUsername(String username) {
		int checkUsername = adminMapper.checkUsername(username);
		if(checkUsername==0) {
			//用户名不重复
			return true;
		}else {
			//用户名重复
			return false;
		}
	}
}
