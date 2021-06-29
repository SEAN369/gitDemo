package com.itheima.services;

import com.github.pagehelper.PageInfo;

import com.itheima.domain.Route;

import java.util.List;

/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public interface RouteService {


    PageInfo<Route> pageSelect(int pageNum, int pageSize);
    List<Route>selectAll();
    void save(Route route);
    Route selectByid(Integer id);
    void update(Route route);

    void  deleteByID(Integer rid);

    PageInfo<Route> selectByidAndAll(int pageNum, int pageSize, int cid);









}
