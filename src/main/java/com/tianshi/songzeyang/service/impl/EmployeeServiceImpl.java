package com.tianshi.songzeyang.service.impl;

import com.tianshi.songzeyang.bean.Employee;
import com.tianshi.songzeyang.mapper.EmployeeMapper;
import com.tianshi.songzeyang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployees()
    {
        return employeeMapper.selectAll();
    }

    @Override
    public List<Employee> getEmployeesByName(String empName)
    {
        // 参数校验：name 不能为 null 或纯空格
        if (!StringUtils.hasText(empName)) {
            throw new IllegalArgumentException("员工姓名不能为空");
        }

        return employeeMapper.selectByName(empName);
    }

    @Override
    public boolean deleteEmployeesByIds(List<Integer> ids)
    {
        // 参数校验：ids 不能为 null 或空集合
        if (CollectionUtils.isEmpty(ids)) {
            throw new IllegalArgumentException("要删除的员工ID列表不能为空");
        }

        // 过滤掉 null 或 <=0 的 id，如果过滤后为空也抛异常
        List<Integer> validIds = ids.stream()
                .filter(id -> id != null && id > 0)
                .collect(Collectors.toList());
        if (validIds.isEmpty()) {
            throw new IllegalArgumentException("员工ID列表中没有有效的ID");
        }

        int rows = employeeMapper.deleteBatch(validIds);
        return rows > 0;
    }
}
