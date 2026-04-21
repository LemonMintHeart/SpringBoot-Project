package com.tianshi.songzeyang.service.impl;

import com.tianshi.songzeyang.bean.Salary;
import com.tianshi.songzeyang.mapper.SalaryMapper;
import com.tianshi.songzeyang.service.SalaryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Resource
    private SalaryMapper salaryMapper;

    /**
     * 新增薪资记录
     */
    public boolean addSalary(Salary salary) {
        // 业务校验：员工ID、薪资金额不能为空
        if (salary.getEmployeeId() == null || salary.getSalaryAmount() == null) {
            return false;
        }
        return salaryMapper.insert(salary) > 0;
    }

    /**
     * 根据ID删除薪资记录
     */
    public boolean removeSalaryById(Integer id) {
        return salaryMapper.deleteById(id) > 0;
    }

    /**
     * 动态更新薪资记录
     */
    public boolean modifySalary(Salary salary) {
        if (salary.getId() == null) {
            return false;
        }
        return salaryMapper.update(salary) > 0;
    }

    /**
     * 查询所有薪资记录
     */
    public List<Salary> selectAll() {
        return salaryMapper.selectAll();
    }

    /**
     * 多条件动态查询
     */
    public List<Salary> querySalaryByCondition(Salary salary) {
        return salaryMapper.selectMultiCondition(salary);
    }

}