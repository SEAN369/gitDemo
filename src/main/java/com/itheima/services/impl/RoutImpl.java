package com.itheima.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import com.itheima.dao.RouteDaoMapper;
import com.itheima.domain.Route;
import com.itheima.services.RouteService;
import com.itheima.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public class RoutImpl implements RouteService {


    @Override
    public PageInfo<Route> pageSelect(int pageNum, int pageSize) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        RouteDaoMapper mapper = sqlSession.getMapper(RouteDaoMapper.class);
        PageHelper.startPage(pageNum,pageSize);
        List<Route> routes = mapper.selectAll();
        PageInfo<Route> pageInfo = new PageInfo<>(routes,10);
        MyBatisUtils.commitAndClose(sqlSession);
        return pageInfo;
    }

    @Override
    public List<Route> selectAll() {

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        RouteDaoMapper mapper = sqlSession.getMapper(RouteDaoMapper.class);
        List<Route> routes = mapper.selectAll();
        MyBatisUtils.commitAndClose(sqlSession);

        return routes;}

    @Override
    public void save(Route route) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        RouteDaoMapper mapper = sqlSession.getMapper(RouteDaoMapper.class);
        mapper.save(route);
        MyBatisUtils.commitAndClose(sqlSession);

    }

    @Override
    public Route selectByid(Integer id) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        RouteDaoMapper mapper = sqlSession.getMapper(RouteDaoMapper.class);
        Route route = mapper.selectByid(id);
        return route;

    }

    @Override
    public void update(Route route) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        RouteDaoMapper mapper = sqlSession.getMapper(RouteDaoMapper.class);
        mapper.update(route);
        MyBatisUtils.commitAndClose(sqlSession);
    }

    @Override
    public void deleteByID(Integer rid) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        RouteDaoMapper mapper = sqlSession.getMapper(RouteDaoMapper.class);
        mapper.deleteByID(rid);
        MyBatisUtils.commitAndClose(sqlSession);
    }

    /**
     * 通过商品分类进行查询
     * 然后通过到导航栏显示
     * @param pageNum  当前第几页
     * @param pageSize 显示几页
     * @param cid       所属的商品分类
     * @return
     */
    @Override
    public PageInfo<Route> selectByidAndAll(int pageNum, int pageSize, int cid) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        RouteDaoMapper mapper = sqlSession.getMapper(RouteDaoMapper.class);
        PageHelper.startPage(pageNum,pageSize);
        List<Route> routes = mapper.selectByidAndAll(cid);
        MyBatisUtils.commitAndClose(sqlSession);
        PageInfo pageInfo = new PageInfo(routes,10);
        return pageInfo;
    }
}
