package com.itheima.services.impl;



import com.itheima.dao.UserMapper;
import com.itheima.utils.MyBatisUtils;

import com.itheima.domain.User;
import com.itheima.services.UserServices;
import org.apache.ibatis.session.SqlSession;

/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public class UserImpl implements UserServices {


    @Override
    public void saveUser(User user) {

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.saveUser(user);
       MyBatisUtils.commitAndClose(sqlSession);
    }

    @Override
    public User loginBtn(User user) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.loginBtn(user);
        MyBatisUtils.commitAndClose(sqlSession);
        return user1;
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.update(user);
        MyBatisUtils.commitAndClose(sqlSession);
    }

}
