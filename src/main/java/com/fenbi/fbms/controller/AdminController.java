package com.fenbi.fbms.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenbi.fbms.entity.Admin;
import com.fenbi.fbms.entity.FenbiResult;
import com.fenbi.fbms.entity.Function;
import com.fenbi.fbms.entity.Role;
import com.fenbi.fbms.service.AdminService;
import com.fenbi.fbms.service.FunctionService;
import com.fenbi.fbms.utils.Md5Util;
/**
 * 管理员
 * @author xulei
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private Md5Util md5Util;

	@RequestMapping("/findAll.do")
	@ResponseBody
	public FenbiResult findAll(String name, int currentPage) {
		List<Admin> admins = adminService.queryAdminsList(name, 2);
		return new FenbiResult(admins);
	}
	/**
	 * 登陆
	 * @param username 用户名
	 * @param password 密码
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public FenbiResult login(String username, String password,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		Admin admin = adminService.login(username, md5Util.md5(password));
		if(admin!=null) {
			//将user对象存入session中
			session.setAttribute("loginAdmin", admin);
			//将用户名保存的cookie中
			Cookie cookie = new Cookie("username",URLEncoder.encode(admin.getName(), "utf-8"));
			cookie.setPath("/");
			response.addCookie(cookie);
			return new FenbiResult(true);
		} 
		return new FenbiResult(FenbiResult.STATUS_FAIL, "账号或密码输入错误", null);
	}
	/**
	 * 注销
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpServletResponse response,HttpSession session) {
		//销毁session
		session.invalidate();
		//清除cookie  
		Cookie cookie = new Cookie("username",null);
		// maxAge : -1等于浏览器关闭时 cookie销毁
		// maxAge : 0 删除cookie
		// maxAge : 10 失效时间
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/login.html";
	}
	/**
	 * 添加管理员
	 * @param admin 管理员
	 * @return
	 */
	@RequestMapping("/addAdmin.do")
	@ResponseBody
	public FenbiResult addAdmin(Admin admin, String repassword) {
		if(admin.getPassword()!=null && !admin.getPassword().equals(repassword)) {
			return new FenbiResult(FenbiResult.STATUS_FAIL, "两次密码输入不一致", null);
		}
		String password = admin.getPassword();
		admin.setPassword(md5Util.md5(password));
		admin.setCreateTime(new Timestamp(new Date().getTime()));
		adminService.add(admin);
		return new FenbiResult();
	}
	/**根据关键字和页码查询 管理员信息列表
	 * @param name 查询关键字/姓名
	 * @param currentPage 当前第几页
	 * @return 管理员列表
	 */
	@RequestMapping("/queryAdminsList.do")
	@ResponseBody
	public FenbiResult queryAdminsList(String keyword,Integer page) {
		List<Admin> roles = adminService.queryAdminsList(keyword, page);
		return new FenbiResult(roles);
	}
	
	/**
	 * 根据关键字查询用户列表，得到总行数
	 * @param name 查询关键字/姓名
	 * @return 管理员列表
	 */
	@RequestMapping("/queryCount.do")
	@ResponseBody
	public FenbiResult queryCount(String keyword) {
		int count = adminService.queryCount(keyword);
		return new FenbiResult(count);
	}
	/**
	 * 根据管理员ID查询该管理员
	 * @param adminId
	 * @return 管理员列表
	 */
	@RequestMapping("/queryAdminByAdminId.do")
	@ResponseBody
	public FenbiResult queryAdminByAdminId(Integer id) {
		Admin admin = adminService.queryById(id);
		return new FenbiResult(admin);
	}
	/**
	 * 修改管理员 
	 * @param admin 管理员
	 * @return 管理员列表
	 */
	@RequestMapping("/modifyAdmin.do")
	@ResponseBody
	public FenbiResult modifyAdmin(Admin admin) {
		adminService.modify(admin);
		return new FenbiResult();
	}
	/**
	 * 根据管理员ID添加该管理员的角色权限
	 * @param adminId 管理员ID
	 * @param roleIds 角色
	 * @return 管理员列表
	 */
	@RequestMapping("/modifyAdminRoles.do")
	@ResponseBody
	public FenbiResult modifyAdminRoles(Integer id,Integer[] roleIds) {
		adminService.modifyAdminRoles(id, roleIds);
		return new FenbiResult();
	}
	/**
	 * 根据管理员ID查询该管理员的角色
	 * @param adminId
	 * @return
	 */
	@RequestMapping("/quaryAdminRoles.do")
	@ResponseBody
	public FenbiResult quaryAdminRoles(Integer adminId) {
		List<Role> roles = adminService.quaryAdminRoles(adminId);
		return new FenbiResult(roles);
	}
	/**
	 * 根据管理员ID删除该管理员
	 * @param adminId 管理员ID
	 * @return
	 */
	@RequestMapping("/removeAdminById.do")
	@ResponseBody
	public FenbiResult removeAdminById(Integer id) {
		adminService.removeAdminById(id);
		return new FenbiResult();
	}
	/**
	 * 检查用户名是否重用
	 * @param username 用户名
	 * @return
	 */
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public FenbiResult checkUsername(String username) {
		Boolean checkUsername = adminService.checkUsername(username);
		if(checkUsername) {
			//用户名不重复
			return new FenbiResult(1);
		}else {
			//用户名重复
			return new FenbiResult(0);
		}
	}

}
