package com.itheima.services.impl;




import com.itheima.dao.CategoryMapper;
import com.itheima.domain.Category;

import com.itheima.services.CategoryService;
import com.itheima.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public class CategoryServiceImpl implements CategoryService {


    @Override
    public List<Category> selectAll() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        List<Category> categories = mapper.selectAll();
       MyBatisUtils.commitAndClose(sqlSession);
        return categories;

    }
}
