package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.Salary;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SalaryMapper
{
    // 新增薪资记录
    int insert(Salary salary);
    // 根据ID删除薪资记录
    int deleteById(Integer id);
    // 多条件动态更新
    int update(Salary salary);
    // 查询所有薪资记录
    List<Salary> selectAll();
    // 多条件动态查询
    List<Salary> selectMultiCondition(Salary salary);
}