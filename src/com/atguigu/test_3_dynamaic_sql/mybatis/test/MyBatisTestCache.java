package com.atguigu.test_3_dynamaic_sql.mybatis.test;

import com.atguigu.test_2_mapper.mybatis.bean.Employee;
import com.atguigu.test_2_mapper.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class MyBatisTestCache {
    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        String resource = "com/atguigu/test_2_mapper/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * mybatis Cache:
     *      1. first level cache (local cache)
     *          -- data queried during one session will be stored on local
     *          -- If running the same query, we can get it from cache directly
     *      2. second level cache (global cache)
     * @throws Exception
     */
    @Test
    public void testFirstLevelCache() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee emp = mapper.getEmpById(1);

            //openSession.clearCache();
            Employee emp2 = mapper.getEmpById(1);
            System.out.println(emp);
            System.out.println(emp==emp2);
        } finally {
            openSession.close();
        }
    }
}
