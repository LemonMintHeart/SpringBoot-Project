package com.tianshi.songzeyang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Department {
    private Integer id;
    private String deptName;
    private Integer managerId;
    private Integer status;
    private LocalDateTime createTime;
}
