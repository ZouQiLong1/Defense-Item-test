package com.manage.ecrewManage.web.servlet.commodiy;

import com.manage.ecrewManage.service.UpdateCommodityService;
import com.manage.ecrewManage.service.UpdateUserService;
import com.manage.ecrewManage.service.impl.UpdateCommodityServiceImpl;
import com.manage.ecrewManage.service.impl.UpdateUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCommodityServlet")
public class DeleteCommodityServlet extends HttpServlet {
    //因为service层是公用的，因此直接放在最外面
    private UpdateCommodityService updateCommodityService = new UpdateCommodityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先接收页面上的参数id
        String id = request.getParameter("id");
        //调用service层删除id对应的user，
        updateCommodityService.deleteUser(Integer.parseInt(id));
        //重新跳转到index.jsp
        response.sendRedirect(request.getContextPath() + "/indexInfoByPageServlet");    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
