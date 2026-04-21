package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper
{
    // 新增部门
    int insert(Department department);

    // 根据ID删除部门
    int deleteById(Integer id);

    // 多条件更新
    int update(Department department);

    // 查询所有部门
    List<Department> selectAll();

    // 多条件查询
    List<Department> selectMultiCondition(Department department);
}