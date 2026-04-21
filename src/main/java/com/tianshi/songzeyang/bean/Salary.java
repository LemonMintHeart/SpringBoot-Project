package com.tianshi.songzeyang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Salary
{
    private Integer id;
    // 员工ID
    private Integer employeeId;
    // 薪资金额
    private BigDecimal salaryAmount;
    // 发放时间
    private LocalDate payDate;
    // 发放状态: 0-待发, 1-已发
    private Integer payStatus;
}