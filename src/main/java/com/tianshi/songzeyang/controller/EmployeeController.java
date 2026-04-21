package com.tianshi.songzeyang.controller;

import com.tianshi.songzeyang.bean.Employee;
import com.tianshi.songzeyang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    // 1. 查询所有员工
    @GetMapping("/showAll")
    public List<Employee> getAllEmployees() {
        try {
            return employeeService.getAllEmployees();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();  // 异常时返回空列表
        }
    }

    // 2. 根据姓名模糊查询
    @GetMapping("/selectByName")
    public List<Employee> getEmployeesByName(@RequestParam("name") String name) {
        try {
            return employeeService.getEmployeesByName(name);
        } catch (IllegalArgumentException e) {
            // 参数异常，返回空列表
            System.out.println(e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // 3. 批量删除
    @DeleteMapping("/deleteByIds")
    public String deleteEmployeesByIds(@RequestParam("ids") List<Integer> ids) {
        try {
            boolean result = employeeService.deleteEmployeesByIds(ids);
            return result ? "批量删除成功" : "批量删除失败";
        } catch (IllegalArgumentException e) {
            return e.getMessage();  // 直接返回异常信息
        } catch (Exception e) {
            e.printStackTrace();
            return "批量删除异常：" + e.getMessage();
        }
    }
}
