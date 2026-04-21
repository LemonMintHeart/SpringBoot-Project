-- 使用数据库
USE springboot_learning;

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