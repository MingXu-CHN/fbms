package com.fenbi.fbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.fbms.entity.Function;
import com.fenbi.fbms.mapper.FunctionMapper;
import com.fenbi.fbms.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	private FunctionMapper functionMapper;

	public List<Function> queryAllFunctions() {
		return functionMapper.selectAllFunctions();
	}

	public List<Function> queryAllByRoleId(int roleId) {
		List<Function> function = functionMapper.selectAllByRoleId(roleId);
		return function;
	}

	public List<Function> queryAllByUserId(int userId) {
		return functionMapper.selectAllByUserId(userId);
	}

}
