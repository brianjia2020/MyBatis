package com.atguigu.test_2_mapper.mybatis.dao;

import com.atguigu.test_2_mapper.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDeptById(Integer id);

    public Employee getEmpByIdStep(Integer id);
}
