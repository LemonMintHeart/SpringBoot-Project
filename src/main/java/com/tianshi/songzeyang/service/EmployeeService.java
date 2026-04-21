package com.tianshi.songzeyang.service;

import com.tianshi.songzeyang.bean.Employee;
import java.util.List;

public interface EmployeeService
{
    boolean addEmployee(Employee employee);

    boolean removeEmployeeById(Integer id);

    boolean removeEmployeesByIds(List<Integer> ids);

    boolean modifyEmployee(Employee employee);

    List<Employee> selectAll();

    List<Employee> queryEmployeeByCondition(Employee employee);
}