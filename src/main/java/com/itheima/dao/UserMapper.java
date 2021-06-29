package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public interface UserMapper {

    @Insert("INSERT INTO  tab_user (username,password,telephone)\n" +
            "VALUES (#{username},#{password},#{telephone})")
   void saveUser(User user);
//    @Select("select * from tab_user")
//    void saveUser();

    /**
     * 这是登陆是的查询
     */
    @Select("SELECT * FROM tab_user WHERE  username=#{username} AND password=#{password}")
    User loginBtn(User user);

    @Update("\n" +
            "UPDATE tab_user SET nickname = #{nickname} ，sex = #{sex},birthday = #{birthday},email = #{email}\n" +
            "WHERE #{uid};")
    void update(User user);
}
