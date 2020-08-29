package com.atguigu.test_3_dynamaic_sql.mybatis.test;


import com.atguigu.test_3_dynamaic_sql.mybatis.bean.Department;
import com.atguigu.test_3_dynamaic_sql.mybatis.bean.Employee;
import com.atguigu.test_3_dynamaic_sql.mybatis.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyBatisTest {

    @Test
    public void test01() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null,null,null,null);
            List<Employee> emp = mapper.getEmpsByConditionIf(employee);
            System.out.println(emp);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test02() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null,null,null,null);
            List<Employee> emp = mapper.getEmpsByConditionTrim(employee);
            emp.forEach(System.out::println);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test03() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(1,null,null,null);
            List<Employee> emp = mapper.getEmpsByConditionChoose(employee);
            emp.forEach(System.out::println);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test04() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(1,"brian",null,null);
            mapper.updateEmp(employee);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test05() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3, 4));
            emps.forEach(System.out::println);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test06() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null,"brian","brianjia@gmail.com","1",new Department(1)));
            emps.add(new Employee(null,"allen","allen123@gmail.com","1",new Department(1)));
            mapper.addEmps(emps);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        String resource = "com/atguigu/test_3_dynamaic_sql/conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
