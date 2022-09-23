package com.sm.ems;

import com.sm.ems.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/index")
    public String index2() {
        return "index2";
    }


}
