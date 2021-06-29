package com.itheima.controller.servlet;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.controller.base.BaseServlet;
import com.itheima.domain.User;
import com.itheima.services.UserServices;
import com.itheima.services.impl.UserImpl;


import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

       private static UserServices userServices = new UserImpl();
    public void saveuser() throws IOException {
        // 获取jason对象的字节流的信息
        ServletInputStream inputStream = request.getInputStream();
        //  将流对象进行转换成json 对象进行转换成功成
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(inputStream, User.class);
        userServices.saveUser(user);
        System.out.println("xieru ");
        response.getWriter().write("ok");
    }

    public void login() throws IOException {
        System.out.println("已进入登录程序");

            ServletInputStream inputStream = request.getInputStream();
            //  将流对象进行转换成json 对象进行转换成功成
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(inputStream, User.class);
        System.out.println("当前用户信息"+user);
            User user1 = userServices.loginBtn(user);
            System.out.println(user1);
            if (user1 != null){
                request.getSession().setAttribute("uer1",user1);
                System.out.println("是否转换成功" + "打印");
                response.getWriter().write("ok");
            }else {
                response.getWriter().write("fail");
            }
        }


    public void checkUserLogin() throws IOException {
        System.out.println("进入登录待检区域");

        User user1 = (User) request.getSession().getAttribute("user1");
            System.out.println(user1);
        ObjectMapper objectMapper = new ObjectMapper();
        String Jason = objectMapper.writeValueAsString(user1);
            System.out.println("转换成jason");
        response.getWriter().write(Jason);

    }
    public void upData(){
     try {
        ServletInputStream inputStream = request.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(inputStream, User.class);
        userServices.update(user);
        request.getSession().setAttribute("user",user);
        response.getWriter().write("ok");

     } catch (JsonParseException e) {
         e.printStackTrace();
     } catch (JsonMappingException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }


    }

}
