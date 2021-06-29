package com.itheima.controller.servlet;


import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageInfo;
import com.itheima.controller.base.BaseServlet;
import com.itheima.domain.Category;
import com.itheima.domain.Route;
import com.itheima.services.CategoryService;
import com.itheima.services.RouteService;
import com.itheima.services.impl.CategoryServiceImpl;
import com.itheima.services.impl.RoutImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
@WebServlet("/RouteServlet")
@MultipartConfig
public class RouteServlet extends BaseServlet {
    private static RouteService routeService = new RoutImpl();

    /**
     *
     * 先进行查询数据库分类然后进行共享添加所有
     * @rid 获取更新是的页数
     * @param request
     * @param response
     */

    public  void toupdate(HttpServletRequest request, HttpServletResponse response) {
        CategoryService categoryService = new CategoryServiceImpl();
        System.out.println("被访问了");
        String rid = request.getParameter("rid");
        System.out.println(rid);
        List<Category> categories = categoryService.selectAll();
        Route routes = routeService.selectByid(Integer.parseInt(rid));
        System.out.println(routes);
        request.setAttribute("routes",routes);
        request.setAttribute("categories",categories);
        try {
            request.getRequestDispatcher("/route_edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @rid 删除是所获取得页面id
     * @param request
     * @param response
     */
    public void deleteByid(HttpServletRequest request, HttpServletResponse response) {
        String rid = request.getParameter("rid");
        System.out.println(rid);
        try {
            if (rid != null && rid != "") {
                routeService.deleteByID(Integer.parseInt(rid));
                response.sendRedirect(request.getContextPath() + "/RouteServlet?action=findAll&pageName=1");
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /*
    添加所有    包括上传图片有问题带改进
    */
    public  void Add(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 参考资料里的和老师的不一样有个问题就是在页面有缩略图但是没有显示完整的图片
            // 发现一个问题就是这个代码版本在out文件目录里这就是最大的原因，它不在当前这个项目
            // 数据库有个问题就是在软件里不显示但是在dosc窗口上不显示（应给是和tomcat有关）
            System.out.println("进入图片添加区");

            Map<String, String[]> parameterMap = request.getParameterMap();
            Route route = new Route();
            BeanUtils.populate(route,parameterMap);
            System.out.println("已经添加进去Utils");
            Part part = request.getPart("rimage");
            System.out.println("正在获取图片数据");
            if (part!= null && part.getSize() >0){
                String FileName = part.getSubmittedFileName();
                System.out.println("正在获取数据名称");
                System.out.println(FileName);
                System.out.println("正在生成UUID");
                FileName = IdUtil.simpleUUID() + FileName;
                System.out.println("再次获取数据名称");
                System.out.println(FileName);
                System.out.println("获取输入刘");
                InputStream inputStream = part.getInputStream();
                System.out.println(inputStream);

                String realPath = getServletContext().getRealPath("/img/product/small");
                System.out.println("生成据绝对路径"+realPath);
                FileOutputStream fileOutputStream = new FileOutputStream("/"+ FileName);
                System.out.println("正在进行流对拷");
                IOUtils.copy(inputStream,fileOutputStream);
                System.out.println("流关闭");
                inputStream.close();
                fileOutputStream.close();
                System.out.println("设置上传路径");
                route.setRimage(realPath + "/" + FileName);
                System.out.println(realPath);
                System.out.println("进行保存");
                routeService.save(route);
                System.out.println("重定向");
                response.sendRedirect(request.getContextPath()+"/RouteServlet?action=findAll&pageName=1");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

     /*
     先查询数据库问题分类的所有然后在进行共享然后再进行添加所有
      */
    public  void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> categories = categoryService.selectAll();
        request.setAttribute("categories",categories);
        request.getRequestDispatcher("/route_add.jsp").forward(request,response);
    }


    /*
    修改全部
    这个有问题
     */
    public  void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            //这里的坑你就是在编辑页面里的form 表单提交的三要素， 还有就会是引入标签的坑就是刚开始的时候
            Map<String, String[]> parameterMap = request.getParameterMap();
//
            Route route = new Route();
            System.out.println(parameterMap);
            System.out.println("进入油条");
            BeanUtils.populate(route,parameterMap);
            System.out.println("正在获得的信息"+route);
            System.out.println("正在修改中");
            routeService.update(route);
            System.out.println("已修改成功");
            System.out.println(route);
            response.sendRedirect(request.getContextPath()+"/RouteServlet?action=findAll&pageName=1");
            System.out.println("已经跳转到首页");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


     /*
     查询所有是想把分类查询了在查所有
     直接是分页查询
      */
    //  http://localhost:8088/modul1_war_exploded/RouteServlet?action=findAll&pageName=5
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("被访问");

        String spageName = request.getParameter("pageName");
        int pageName = 1;
        int pageSize = 10;
        if (spageName != null && spageName != "" && spageName != "0"){
            pageName = Integer.parseInt(spageName);
        }
        PageInfo<Route> pageInfo = routeService.pageSelect(pageName, pageSize);
        System.out.println(pageInfo);
        if (pageInfo != null){
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("/route_list.jsp").forward(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/error.jsp");

        }


    }

    /**
     * service的层面的业务逻辑进行分页查询
     *
     */
    // url :  http://localhost:8080/myPro/RouteServlet?action=selectByIDAnAll&pageNum=1&cid=5
    public  void  selectByIDAnAll(){
        try {
        String pageNum1 = request.getParameter("pageNum");
        int pageNum = 1;
        int pageSize = 10;
        int cid = Integer.parseInt(request.getParameter("cid"));
        if (pageNum1 != null && pageNum1 !="" && pageNum1 != "0"){
            pageNum = Integer.parseInt(pageNum1);
        }
        PageInfo<Route> pageInfo = routeService.selectByidAndAll(pageNum, pageSize, cid);
        request.setAttribute("pageInfo",pageInfo);
        request.getRequestDispatcher("/route_list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


