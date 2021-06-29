package com.itheima.services;


import com.itheima.domain.User;



/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public interface UserServices {
    void saveUser(User user);
    User loginBtn(User user);
    void update(User user);
}
