package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper
{
    // 查询所有员工
    List<Employee> selectAll();

    // 根据姓名模糊查询
    List<Employee> selectByName(@Param("empName") String empName);

    // 批量删除（根据id列表）
    int deleteBatch(@Param("ids") List<Integer> ids);
}
