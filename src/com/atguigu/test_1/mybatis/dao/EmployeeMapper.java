package com.atguigu.test_1.mybatis.dao;

import com.atguigu.test_1.mybatis.bean.Employee;

public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
    public Employee selectEmp(Integer id);
}
