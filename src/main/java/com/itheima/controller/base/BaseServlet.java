package com.itheima.controller.base;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Base继承HttpServlet，具备doGet和doPost方法
 * 所有逻辑均在该两个方法中执行
 */

public abstract class BaseServlet extends HttpServlet {

    //定义成员变量，使用protected修饰，为了子类任意方法中可以直接使用
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通用逻辑
        this.request = request;
        this.response = response;

        //接收用户操作
        String methodString = request.getParameter("action");

        try {
            //根据action的属性(该属性值会与方法名相同！)，反射方法，并调用方法
            //获取包含方法的字节码文件对象
            Class clazz = this.getClass();
            //根据方法名，反射方法
            Method method =  clazz.getDeclaredMethod(methodString);
            //暴力反射
            method.setAccessible(true);
            //执行方法
            method.invoke(this);
        }catch (Exception e) {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
