package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper
{
    // 新增部门
    int insert(Department department);
}
