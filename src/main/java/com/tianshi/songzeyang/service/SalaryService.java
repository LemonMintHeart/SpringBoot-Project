package com.tianshi.songzeyang.service;

import com.tianshi.songzeyang.bean.Salary;
import java.util.List;

public interface SalaryService
{
    boolean addSalary(Salary salary);

    boolean removeSalaryById(Integer id);

    boolean removeSalariesByIds(List<Integer> ids);

    boolean modifySalary(Salary salary);

    List<Salary> selectAll();

    List<Salary> querySalaryByCondition(Salary salary);
}