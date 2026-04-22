package com.tianshi.songzeyang.controller;

import com.tianshi.songzeyang.bean.Employee;
import com.tianshi.songzeyang.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    // 非空校验工具
    private boolean isBlank(String str)
    {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 新增员工
     */
    @PostMapping("/add")
    public String add(@RequestBody Employee employee)
    {
        // 1. 参数完整性校验
        if (isBlank(employee.getEmpNo()) || isBlank(employee.getEmpName())) {
            return "新增失败！原因：工号/姓名不能为空";
        }
        if (employee.getStatus() == null) {
            // 默认在职
            employee.setStatus(1);
        }

        // 2. 调用Service执行新增
        boolean flag = employeeService.addEmployee(employee);
        if (flag) {
            return "新增成功！";
        } else {
            return "新增失败！未知原因！";
        }
    }

    /**
     * 根据ID删除员工
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        if (id == null) return "删除失败！原因：员工ID为空";

        boolean flag = employeeService.removeEmployeeById(id);
        if (flag) {
            return "删除成功！";
        } else {
            return "删除失败！原因：员工不存在";
        }
    }

    /**
     * 批量删除员工
     */
    @DeleteMapping("/delete/batch")
    public String deleteBatch(@RequestParam List<Integer> ids)
    {
        if (ids == null || ids.isEmpty()) {
            return "批量删除失败！原因：ID列表不能为空";
        }
        boolean flag = employeeService.removeEmployeesByIds(ids);
        if (flag) {
            return "批量删除成功！";
        } else {
            return "批量删除失败！未知原因！";
        }
    }

    /**
     * 多条件更新员工
     */
    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                         @RequestBody Employee employee)
    {
        employee.setId(id);

        if (employee.getId() == null) return "更新失败！原因：员工ID不能为空";

        boolean flag = employeeService.modifyEmployee(employee);
        if (flag) {
            return "更新成功！";
        } else {
            return "更新失败！原因：员工不存在";
        }
    }

    /**
     * 根据姓名模糊查询
     **/
    @GetMapping("/list")
    public List<Employee> list(@RequestParam(required = false) String empName) {
        if (isBlank(empName)) {
            // 无参数：查询所有员工
            return employeeService.selectAll();
        } else {
            // 有参数：按姓名模糊查询
            return employeeService.queryEmployeeByEmpNameLike(empName);
        }
    }

    /**
     * 多条件查询
     */
    @GetMapping("/condition")
    public List<Employee> queryCondition(Employee employee) {
        return employeeService.queryEmployeeByCondition(employee);
    }
}