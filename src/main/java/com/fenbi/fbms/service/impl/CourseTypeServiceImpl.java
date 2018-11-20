/**
 * @(#)CourseTypeServiceImpl.java, 2018年3月8日. 
 * 
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.fbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.fbms.entity.CourseType;
import com.fenbi.fbms.mapper.CourseTypeMapper;
import com.fenbi.fbms.service.CourseTypeService;

/**
 * @author heqiang
 *
 */
@Service
public class CourseTypeServiceImpl implements CourseTypeService{

    @Autowired
    CourseTypeMapper courseTypeMapper;
    
    public List<CourseType> queryAll() {
        return courseTypeMapper.selectAll();
    }
    

}
