package com.atguigu.test_2_mapper.mybatis.dao;

import com.atguigu.test_2_mapper.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;


public interface EmployeeMapperAnnotation {

    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
