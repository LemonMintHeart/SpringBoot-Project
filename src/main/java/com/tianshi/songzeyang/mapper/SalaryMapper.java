package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SalaryMapper
{
    // 新增薪资记录
    int insert(@Param("salary") Salary salary);

    // 根据ID删除薪资记录
    int deleteById(@Param("id") Integer id);

    // 批量删除用户
    int deleteByIds(@Param("ids") List<Integer> ids);

    // 多条件动态更新
    int update(@Param("salary") Salary salary);

    // 查询所有薪资记录
    List<Salary> selectAll();

    // 多条件动态查询
    List<Salary> selectMultiCondition(@Param("salary") Salary salary);
}