package com.atguigu.test_2_mapper.mybatis.test;

import com.atguigu.test_2_mapper.mybatis.bean.Department;
import com.atguigu.test_2_mapper.mybatis.bean.Employee;
import com.atguigu.test_2_mapper.mybatis.dao.DepartmentMapper;
import com.atguigu.test_2_mapper.mybatis.dao.EmployeeMapper;
import com.atguigu.test_2_mapper.mybatis.dao.EmployeeMapperAnnotation;
import com.atguigu.test_2_mapper.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 1. interface programming
 *      original way: Dao =====> DaoImpl
 *      mybatis way:  Mapper =====> xxMapper.xml
 * 2. sqlSession object represent connection to database
 * 3. sqlSession is equivalent to Connection and is not thread-safe
 * 4. mapper interface has no implemented class but mybatis will generate a proxy class
 *          EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class)
 * 5. two important configuration xml files
 *      1.mybatis-com.atguigu.test_1.config.xml ===> including environments,database credentials
 *      2.actual class mappers ====> sql statements, mapper
 */
public class MyBatisTest {

    /**
     * 1. based on configuration file, create a sqlSessionFactory bean
     * 2. build a sqlSession from sqlSessionFactory
     * 3. register the sql mapper into the global configuration file
     * 4. write the code (get the sqlSessionFactory and use the sql unique identifier)
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        //1. get sqlSessionFunction
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.get sqlSession object
        SqlSession openSession = sqlSessionFactory.openSession();
        //3.get the mapper
        //  mybatis will automatically create a proxy class for the interface
        //  and the proxy will run that implemented class
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test03() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee emp = mapper.getEmpById(1);
            System.out.println(emp);
        } finally {
            openSession.close();
        }
    }

    /**
     * test CRUD operation
     */
    @Test
    public void test04() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //this openSession does not do auto-commit
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null,"jerry123","jerry@163.com","0");
            mapper.addEmp(employee);
            System.out.println(employee.getId());
//            Employee employee = new Employee(1,"jerry","jerry@163.com","1");
//            mapper.updateEmp(employee);
//            mapper.deleteEmpById(2);
            openSession.commit();
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test05() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Employee> empList = mapper.getEmpByLastNameLike("%j%");
            empList.forEach(System.out::println);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test06() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
            System.out.println(map);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test07() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Employee> map = mapper.getEmpByLastNameLikeReturnMap("%e%");
            System.out.println(map);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test08() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee emp = mapper.getEmpById(1);
            System.out.println(emp);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test09() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee emp = mapper.getEmpAndDeptById(1);
            System.out.println(emp);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test10() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee emp = mapper.getEmpByIdStep(1);
            System.out.println(emp);
            System.out.println(emp.getDept());
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test11() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department dept = mapper.getDeptById(1);
            System.out.println(dept);
            System.out.println(dept.getEmps());

        } finally {
            openSession.close();
        }
    }





    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        String resource = "com/atguigu/test_2_mapper/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
