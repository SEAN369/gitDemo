package com.itheima.dao;


import com.itheima.domain.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteDao {
    //展示分类数据
    List<Route> findRoute(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    //查询总记录数
    int findRecord();

}
