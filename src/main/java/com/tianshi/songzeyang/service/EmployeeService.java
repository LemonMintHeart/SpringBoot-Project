package com.tianshi.songzeyang.service;

import com.tianshi.songzeyang.bean.Employee;

import java.util.List;

public interface EmployeeService
{
    // 查询所有员工
    List<Employee> getAllEmployees();

    // 根据姓名查询员工
    List<Employee> getEmployeesByName(String empName);

    // 根据id删除员工
    boolean deleteEmployeesByIds(List<Integer> ids);
}
