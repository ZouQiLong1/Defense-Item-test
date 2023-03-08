package com.manage.ecrewManage.web.servlet.user;

import com.manage.ecrewManage.service.UpdateUserService;
import com.manage.ecrewManage.service.impl.UpdateUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteChooseServlet")
public class DeleteChooseServlet extends HttpServlet {
    //因为service层是公用的，因此直接放在最外面
    private UpdateUserService updateUserService = new UpdateUserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先接收页面上的参数id
        String[] ids = request.getParameterValues("ids");
        //调用service层删除id对应的user，
        updateUserService.deleteSelected(ids);
        //重新跳转到index.jsp
        response.sendRedirect(request.getContextPath() + "/indexInfoByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
