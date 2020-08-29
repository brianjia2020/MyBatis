package com.atguigu.test_2_mapper.mybatis.dao;

import com.atguigu.test_2_mapper.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    @MapKey("id")
    public Map<String,Employee> getEmpByLastNameLikeReturnMap(String lastName);
    //return a map, key is the column name, value is the corresponding value
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    public List<Employee> getEmpByLastNameLike(String lastName);
    public Employee getEmpById(Integer id);

    public void addEmp(Employee employee);
    public void updateEmp(Employee employee);
    public void deleteEmpById(Integer id);
}
