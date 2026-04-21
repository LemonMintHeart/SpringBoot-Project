package com.tianshi.songzeyang.service;

import com.tianshi.songzeyang.bean.Department;

import java.util.List;

public interface DepartmentService
{
    boolean addDepartment(Department department);

    boolean removeDepartmentById(Integer id);

    boolean modifyDepartment(Department department);

    List<Department> selectAll();

    List<Department> queryDepartmentByCondition(Department department);
}