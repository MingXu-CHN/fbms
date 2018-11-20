/**
 * @(#)CourseTypeMapper.java, 2018年3月8日. 
 * 
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.fbms.mapper;

import java.util.List;

import com.fenbi.fbms.entity.CourseType;

/**
 * @author heqiang
 *
 */
public interface CourseTypeMapper {
    /**
     * 
     * @return  直播课列表对象
     */
    List<CourseType> selectAll();
    

}
