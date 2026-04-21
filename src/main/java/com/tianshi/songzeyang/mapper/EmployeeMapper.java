package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface EmployeeMapper
{
    // 新增员工
    int insert(@Param("emp") Employee employee);

    // 根据ID删除员工
    int deleteById(@Param("id") Integer id);

    // 批量删除用户
    int deleteByIds(@Param("ids") List<Integer> ids);

    // 多条件更新
    int update(@Param("emp") Employee employee);

    // 查询所有员工
    List<Employee> selectAll();

    // 多条件查询
    List<Employee> selectMultiCondition(@Param("emp") Employee employee);
}