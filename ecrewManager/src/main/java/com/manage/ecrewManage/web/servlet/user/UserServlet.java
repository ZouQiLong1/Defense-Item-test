package com.manage.ecrewManage.web.servlet.user;

import com.manage.ecrewManage.web.servlet.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //获取用
    protected void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
