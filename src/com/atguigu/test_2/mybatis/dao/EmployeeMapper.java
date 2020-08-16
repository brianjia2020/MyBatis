package com.atguigu.test_2.mybatis.dao;

import com.atguigu.test_2.mybatis.bean.Employee;

public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public void addEmp(Employee employee);
    public void updateEmp(Employee employee);
    public void deleteEmpById(Integer id);
}
