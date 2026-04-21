package com.tianshi.songzeyang.service.impl;

import com.tianshi.songzeyang.bean.Department;
import com.tianshi.songzeyang.mapper.DepartmentMapper;
import com.tianshi.songzeyang.service.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 新增部门
     */
    public boolean addDepartment(Department department) {
        // 业务校验：部门名称非空（可根据需求扩展）
        if (department.getDeptName() == null || department.getDeptName().trim().isEmpty()) {
            return false;
        }
        return departmentMapper.insert(department) > 0;
    }

    /**
     * 根据ID删除部门
     */
    public boolean removeDepartmentById(Integer id) {
        return departmentMapper.deleteById(id) > 0;
    }

    /**
     * 多条件更新部门
     */
    public boolean modifyDepartment(Department department) {
        if (department.getId() == null) {
            return false;
        }
        return departmentMapper.update(department) > 0;
    }

    /**
     * 查询所有部门
     */
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    /**
     * 多条件精确查询（通用）
     */
    public List<Department> queryDepartmentByCondition(Department department) {
        return departmentMapper.selectMultiCondition(department);
    }

}