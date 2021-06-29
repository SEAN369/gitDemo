package com.itheima.dao;



import com.itheima.domain.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public interface CategoryMapper {
    @Select("SELECT * FROM tab_category WHERE cid = #{cid} ")
    Category selectByID(Integer cid);

    @Select("select * from tab_category")
    List<Category> selectAll();

}
