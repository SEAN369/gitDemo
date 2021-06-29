package com.itheima.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 提取重复代码
 * 1.对外提供工厂对象
 * 2.对外提供sqlSession对象
 */
public class MyBatisUtils {

    private static   SqlSessionFactory sqlSessionFactory = null;
    static {
        try {
            //加载流
            InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
            //解析流
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("配置文件加载异常");
        }
    }

    //1.对外提供工厂对象
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
    //2.对外提供sqlSession对象
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
    //3.提交事务 并释放资源
    public static void commitAndClose(SqlSession sqlSession){
        //5.释放资源
        sqlSession.commit();
        sqlSession.close();
    }
}
