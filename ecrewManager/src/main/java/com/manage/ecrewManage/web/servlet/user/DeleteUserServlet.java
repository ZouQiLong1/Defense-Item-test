package com.manage.ecrewManage.web.servlet.user;

import com.manage.ecrewManage.service.UpdateUserService;
import com.manage.ecrewManage.service.impl.UpdateUserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    //因为service层是公用的，因此直接放在最外面
    private UpdateUserService updateUserService = new UpdateUserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先接收页面上的参数id
        String id = request.getParameter("id");
        //调用service层删除id对应的user，
        updateUserService.deleteUser(Integer.parseInt(id));
        //重新跳转到index.jsp
        response.sendRedirect(request.getContextPath() + "/indexInfoByPageServlet");    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
