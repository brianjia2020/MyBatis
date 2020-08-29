package com.atguigu.test_3_dynamaic_sql.mybatis.bean;

import com.atguigu.test_3_dynamaic_sql.mybatis.bean.Employee;

import java.util.List;

public class Department {
    private Integer id;
    private String departmentName;
    private List<com.atguigu.test_3_dynamaic_sql.mybatis.bean.Employee> emps;

    public List<com.atguigu.test_3_dynamaic_sql.mybatis.bean.Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", emps=" + emps +
                '}';
    }

    public Department(Integer id, String departmentName, List<Employee> emps) {
        this.id = id;
        this.departmentName = departmentName;
        this.emps = emps;
    }

    public Department() {
    }

    public Department(Integer id) {
        this.id = id;
    }
}
