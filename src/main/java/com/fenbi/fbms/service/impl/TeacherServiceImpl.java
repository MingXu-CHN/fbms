package com.fenbi.fbms.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.fbms.entity.Page;
import com.fenbi.fbms.entity.Teacher;
import com.fenbi.fbms.exception.UpdateStatusFailException;
import com.fenbi.fbms.mapper.TeacherMapper;
import com.fenbi.fbms.service.TeacherService;

/**
 * @author heqiang
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public void save(Teacher teacher) {
        teacher.setCreateTime(new Timestamp(System.currentTimeMillis()));
        teacherMapper.insert(teacher);
    }

    public List<Teacher> queryAll(String key, int currentPage) {
        Page page = new Page(currentPage);
        page.put("name", key);
        return teacherMapper.selectAll(page);
    }

    public Teacher queryById(int id) {
        return teacherMapper.selectById(id);
    }

    public void modify(Teacher teacher) {
        teacherMapper.update(teacher);

    }

    public void modifyStatus(int teacherId, int status)  throws UpdateStatusFailException{
    	Teacher teacher = teacherMapper.selectById(teacherId);
    	if(teacher!=null && teacher.getStatus()!=status) {
    		teacherMapper.updateStatus(teacherId, status);
    	}else {
    		throw new UpdateStatusFailException("不需要修改讲师状态");
    	}
    	
    }

    public int queryCount(String key) {
        int count = teacherMapper.selectCount(key);
        return count;
    }
    
    /**查询所有的老师
	 * @return 返回所有的老师名字
	 */
	public List<Teacher> queryTeacherNames() {
		return teacherMapper.selectTeacherNames();
	}

}
