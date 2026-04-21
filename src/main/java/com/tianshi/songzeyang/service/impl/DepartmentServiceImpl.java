package com.tianshi.songzeyang.service.impl;

import com.tianshi.songzeyang.bean.Department;
import com.tianshi.songzeyang.mapper.DepartmentMapper;
import com.tianshi.songzeyang.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService
{
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public boolean addDepartment(Department department)
    {
        // 参数校验：department 不能为 null，且部门名称不能为空
        if (department == null) {
            throw new IllegalArgumentException("部门信息不能为空");
        }
        if (!StringUtils.hasText(department.getDeptName())) {
            throw new IllegalArgumentException("部门名称不能为空");
        }

        // 设置默认值（如果前端未传）
        if (department.getStatus() == null) {
            department.setStatus(1);   // 默认启用
        }
        if (department.getCreateTime() == null) {
            department.setCreateTime(LocalDateTime.now());
        }

        int rows = departmentMapper.insert(department);
        return rows > 0;
    }
}
