package com.manage.ecrewManage.web.servlet.commodiy;

import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.domain.User;
import com.manage.ecrewManage.service.UpdateCommodityService;
import com.manage.ecrewManage.service.UpdateUserService;
import com.manage.ecrewManage.service.impl.UpdateCommodityServiceImpl;
import com.manage.ecrewManage.service.impl.UpdateUserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addCommodityServlet")
public class addCommodityServlet extends HttpServlet {
    private UpdateCommodityService updateCommodityService = new UpdateCommodityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面上接收参数，
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建一个user对象，保存user信息
        Commodity commodity = new Commodity();
        //将页面上接收的信息，封装到user对象中
        try {
            BeanUtils.populate(commodity,parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //调用service层添加user
        boolean flag = updateCommodityService.addUser(commodity);
        //如果flage为真，就是插入成功
        if (flag){
            //重定向到首页
            response.sendRedirect(request.getContextPath() + "/goodsInfoByPageServlet");
        } else {
            //flag为假，也就是用户名已存在
            request.setAttribute("err_msg","用户已存在");
            request.getRequestDispatcher("/addUser.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
