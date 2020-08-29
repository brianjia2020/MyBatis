package com.atguigu.test_3_dynamaic_sql.mybatis.dao;

import com.atguigu.test_3_dynamaic_sql.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(List<Integer> items);

    public void addEmps(@Param("emps") List<Employee> employees);
}
