package com.tianshi.songzeyang.service.impl;

import com.tianshi.songzeyang.bean.Employee;
import com.tianshi.songzeyang.mapper.EmployeeMapper;
import com.tianshi.songzeyang.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 新增员工
     */
    public boolean addEmployee(Employee employee) {
        // 业务校验：工号和姓名非空
        if (employee.getEmpNo() == null || employee.getEmpNo().trim().isEmpty() ||
                employee.getEmpName() == null || employee.getEmpName().trim().isEmpty()) {
            return false;
        }
        return employeeMapper.insert(employee) > 0;
    }

    /**
     * 根据ID删除员工
     */
    public boolean removeEmployeeById(Integer id) {
        return employeeMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除员工
     */
    public boolean removeEmployeesByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return employeeMapper.deleteByIds(ids) > 0;
    }

    /**
     * 多条件更新员工
     */
    public boolean modifyEmployee(Employee employee) {
        if (employee.getId() == null) {
            return false;
        }
        return employeeMapper.update(employee) > 0;
    }

    /**
     * 查询所有员工
     */
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    /**
     * 根据姓名模糊查询
     */
    @Override
    public List<Employee> queryEmployeeByEmpNameLike(String empName) {
        return employeeMapper.selectByEmpNameLike(empName);
    }

    /**
     * 多条件精确查询
     */
    public List<Employee> queryEmployeeByCondition(Employee employee) {
        return employeeMapper.selectMultiCondition(employee);
    }

}