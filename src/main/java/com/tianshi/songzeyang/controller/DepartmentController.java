package com.tianshi.songzeyang.controller;

import com.tianshi.songzeyang.bean.Department;
import com.tianshi.songzeyang.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public String addDepartment(@RequestBody Department department) {
        try {
            boolean result = departmentService.addDepartment(department);
            if (result) {
                return "新增部门成功，部门ID：" + department.getId();
            } else {
                return "新增部门失败";
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();  // 如“部门名称不能为空”
        } catch (Exception e) {
            e.printStackTrace();
            return "新增部门异常：" + e.getMessage();
        }
    }
}
