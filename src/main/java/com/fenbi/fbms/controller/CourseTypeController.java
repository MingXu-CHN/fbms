/**
 * @(#)CourseTypeController.java, 2018年3月8日. 
 * 
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.fbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenbi.fbms.entity.CourseType;
import com.fenbi.fbms.entity.FenbiResult;
import com.fenbi.fbms.service.CourseTypeService;

/**
 * @author heqiang
 *
 */
@Controller
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    CourseTypeService courseTypeService;
    
    @RequestMapping("/queryAll")
    @ResponseBody
    public FenbiResult queryAll() {
        List<CourseType> list = courseTypeService.queryAll();
        return new FenbiResult(list);
    }
}
