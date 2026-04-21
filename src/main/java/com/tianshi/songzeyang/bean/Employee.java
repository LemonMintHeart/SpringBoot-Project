package com.tianshi.songzeyang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee
{
    private Integer id;
    private String empNo;
    private String empName;
    private Integer gender;
    private String phone;
    private String email;
    private Integer departmentId;
    private Integer status;
    private LocalDate hireDate;
}
