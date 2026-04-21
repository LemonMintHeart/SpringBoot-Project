package com.tianshi.songzeyang.controller;

import com.tianshi.songzeyang.bean.Salary;
import com.tianshi.songzeyang.service.SalaryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Resource
    private SalaryService salaryService;

    /**
     * 新增薪资记录
     */
    @PostMapping("/add")
    public String add(@RequestBody Salary salary)
    {
        // 1. 参数校验
        if (salary.getEmployeeId() == null || salary.getSalaryAmount() == null) {
            return "新增失败！原因：员工ID/薪资金额不能为空";
        }
        // 默认状态：待发放
        if (salary.getPayStatus() == null) {
            salary.setPayStatus(0);
        }

        // 2. 执行新增
        boolean flag = salaryService.addSalary(salary);
        if (flag) {
            return "新增成功！";
        } else {
            return "新增失败！未知原因！";
        }
    }

    /**
     * 根据ID删除薪资记录
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        if (id == null) {
            return "删除失败！原因：薪资ID为空";
        }

        boolean flag = salaryService.removeSalaryById(id);
        if (flag) {
            return "删除成功！";
        } else {
            return "删除失败！原因：记录不存在";
        }
    }

    /**
     * 批量删除薪资记录
     */
    @DeleteMapping("/delete/batch")
    public String deleteBatch(@RequestParam List<Integer> ids)
    {
        if (ids == null || ids.isEmpty()) {
            return "批量删除失败！原因：ID列表不能为空";
        }
        boolean flag = salaryService.removeSalariesByIds(ids);
        if (flag) {
            return "批量删除成功！";
        } else {
            return "批量删除失败！未知原因！";
        }
    }

    /**
     * 动态更新薪资记录
     */
    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                         @RequestBody Salary salary)
    {
        salary.setId(id);
        if (salary.getId() == null) {
            return "更新失败！原因：薪资ID不能为空";
        }

        boolean flag = salaryService.modifySalary(salary);
        if (flag) {
            return "更新成功！";
        } else {
            return "更新失败！原因：记录不存在";
        }
    }

    /**
     * 查询所有薪资记录
     */
    @GetMapping("/list")
    public List<Salary> list() {
        return salaryService.selectAll();
    }

    /**
     * 多条件查询
     */
    @GetMapping("/condition")
    public List<Salary> queryCondition(Salary salary) {
        return salaryService.querySalaryByCondition(salary);
    }
}