-- 使用数据库
USE springboot_learning;

-- 用户表
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名（唯一）',
    password VARCHAR(20) NOT NULL COMMENT '密码'
) COMMENT='用户表';

-- 部门表
CREATE TABLE department (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '部门ID',
    dept_name VARCHAR(20) NOT NULL COMMENT '部门名称',
    manager_id INT COMMENT '部门负责人ID（引用employee.id）',
    status TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态: 1-启用, 0-停用',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '部门创建时间'
) COMMENT='部门表';

-- 员工表
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '员工ID',
    emp_no VARCHAR(20) NOT NULL UNIQUE COMMENT '工号',
    emp_name VARCHAR(20) NOT NULL COMMENT '姓名',
    gender TINYINT(1) NOT NULL DEFAULT 0 COMMENT '性别: 1-男, 2-女, 0-未知',
    phone VARCHAR(20) COMMENT '手机号',
    email VARCHAR(20) COMMENT '邮箱',
    department_id INT COMMENT '部门ID',
    status TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态: 1-在职, 2-试用期, 3-离职, 4-休假',
    hire_date DATE COMMENT '入职日期',
    FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE SET NULL
) COMMENT='员工表';

-- 薪资表
CREATE TABLE salary (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '薪资记录id',
    employee_id INT NOT NULL COMMENT '员工ID',
    salary_amount DECIMAL(10,2) NOT NULL COMMENT '薪资金额',
    pay_date DATE COMMENT '发放日期',
    pay_status TINYINT(1) NOT NULL DEFAULT 0 COMMENT '发放状态: 0-待发, 1-已发'
) COMMENT='薪资表';