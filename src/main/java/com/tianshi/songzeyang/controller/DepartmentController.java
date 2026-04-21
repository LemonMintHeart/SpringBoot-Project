package com.tianshi.songzeyang.controller;

import com.tianshi.songzeyang.bean.Department;
import com.tianshi.songzeyang.service.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    // 非空校验工具
    private boolean isBlank(String str)
    {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 新增部门
     */
    @PostMapping("/add")
    public String add(@RequestBody Department department)
    {
        // 1. 参数完整性校验
        if (isBlank(department.getDeptName())) {
            return "新增失败！原因：部门名称不能为空";
        }
        if (department.getStatus() == null) {
            // 默认启用
            department.setStatus(1);
        }

        // 2. 调用Service执行新增
        boolean flag = departmentService.addDepartment(department);
        if (flag) {
            return "新增成功！";
        } else {
            return "新增失败！未知原因！";
        }
    }

    /**
     * 根据ID删除部门
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        if (id == null) return "删除失败！原因：部门ID为空";

        boolean flag = departmentService.removeDepartmentById(id);
        if (flag) {
            return "删除成功！";
        } else {
            return "删除失败！原因：部门不存在";
        }
    }

    /**
     * 批量删除部门
     */
    @DeleteMapping("/delete/batch")
    public String deleteBatch(@RequestParam List<Integer> ids)
    {
        if (ids == null || ids.isEmpty()) {
            return "批量删除失败！原因：ID列表不能为空";
        }
        boolean flag = departmentService.removeDepartmentsByIds(ids);
        if (flag) {
            return "批量删除成功！";
        } else {
            return "批量删除失败！未知原因！";
        }
    }

    /**
     * 多条件更新部门
     */
    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                         @RequestBody Department department)
    {
        department.setId(id);

        if (department.getId() == null) return "更新失败！原因：部门ID不能为空";

        boolean flag = departmentService.modifyDepartment(department);
        if (flag) {
            return "更新成功！";
        } else {
            return "更新失败！原因：部门不存在";
        }
    }

    /**
     * 查询所有部门
     */
    @GetMapping("/list")
    public List<Department> list() {
        return departmentService.selectAll();
    }

    /**
     * 多条件查询
     */
    @GetMapping("/condition")
    public List<Department> queryCondition(Department department) {
        return departmentService.queryDepartmentByCondition(department);
    }
}