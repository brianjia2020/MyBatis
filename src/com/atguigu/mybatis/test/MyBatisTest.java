package com.atguigu.mybatis.test;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * 1. interface programming
 *      original way: Dao =====> DaoImpl
 *      mybatis way:  Mapper =====> xxMapper.xml
 * 2. sqlSession object represent connection to database
 * 3. sqlSession is equivalent to Connection and is not thread-safe
 * 4. mapper interface has no implemented class but mybatis will generate a proxy class
 *          EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class)
 * 5. two important configuration xml files
 *      1.mybatis-config.xml ===> including environments,database credentials
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

    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
