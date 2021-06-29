package com.itheima.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.controller.base.BaseServlet;
import com.itheima.domain.Category;
import com.itheima.services.CategoryService;
import com.itheima.services.impl.CategoryServiceImpl;


import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;


/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    /**
     * 使用异步后端代码响应客户段
     * 使用redis 的时候返回值用String
     */
    // http://localhost:8080/myPro/CategoryServlet?action=CategorySellAll
    public  void CategorySellAll() throws IOException {
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> categories = categoryService.selectAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String categJson = objectMapper.writeValueAsString(categories);
        response.getWriter().write(categJson);
        System.out.println(categJson);

//        return categJson;
    }


    /**使用redis进行数据的海量访问
     * 这种写法值得借鉴
     * @throws IOException
     */

//    public void  getJedis() throws IOException {
//
//        // 再次可以对获取的数据的方式进行捕获判段
//        Jedis jedis = null;
//        try {
//            // 如果有缓存总获取数据否则直接获取从数据库获取
//      jedis = RedisUtils.getJedis();
//
//        }catch (Exception e){
//            System.out.println("从数据库中获取数据");
//            response.getWriter().write(CategorySellAll());
//            return;
//        }
//        // 先从缓存中获取数据
//        String categories = jedis.get("categories");
//        if (categories == null || categories.equals("")){
//            System.out.println("从数据库中获取数据");
//            // 存入缓存中
//             categories = CategorySellAll();
//            jedis.set("categories",categories);
//        }else{
//            System.out.println("从缓存中获取数据");
//        }
//        //
//        jedis.close();
//        // 无轮从哪获取数据都发送到前段是到header页面
//        response.getWriter().write(categories);
//    }


            // 从缓存中获取数据




        // 如果没有数据

            //把它放到缓存区供给在次使用

        // 无论从哪读取数据都要响应、








}

