package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EmployeeMapper
{
    // 新增员工
    int insert(Employee employee);
    // 根据ID删除员工
    int deleteById(Integer id);
    // 多条件更新
    int update(Employee employee);
    // 查询所有员工
    List<Employee> selectAll();
    // 多条件查询
    List<Employee> selectMultiCondition(Employee employee);
}