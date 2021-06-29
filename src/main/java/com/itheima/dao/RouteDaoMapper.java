package com.itheima.dao;


import com.itheima.domain.Route;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public interface RouteDaoMapper {

        @Results({
            @Result(column = "rid",property = "rid" ,id = true),
            @Result(column = "cid",property = "cid"),
            @Result(column = "cid",property = "category",
                one = @One(select = "com.com.itheima.dao.CategoryMapper.selectByID"))

    })
    @Select("SELECT * FROM tab_route")
    List<Route>selectAll();

        @Select("SELECT * FROM tab_route WHERE rid = #{rid};")
       Route selectByid(Integer id);





        @Insert("insert into tab_route (rid,rname,price,routeIntroduce,cid,rimage)" +
                "values (null,#{rname},#{price},#{routeIntroduce},#{cid},#{rimage})")
       void save(Route route);

        @Delete("DELETE FROM tab_route WHERE rid =#{rid};")
        void  deleteByID(Integer rid);

        @Update("UPDATE tab_route SET  rname = #{rname},price = #{price} , routeIntroduce = #{routeIntroduce} , cid = #{cid}  WHERE rid = #{rid};")
        void update(Route route);

        /*
        开始导航分页查询
         */
        @Select("SELECT * FROM tab_route WHERE cid=#{cid}")
       List<Route>selectByidAndAll(Integer id);


}
//


//    @Select("SELECT DISTINCT tb.rid , tb.rname, tb.price ,tb.routeIntroduce,tb.rdate,tc.cid,tc.cname,tb.rimage\n" +
//            "FROM tab_route AS tb ,tab_category AS tc\n" +
//            "WHERE tb.cid =tc.cid")
//    List<Route>selectAll();