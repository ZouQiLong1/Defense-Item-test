package com.manage.ecrewManage.web.servlet.friend;

import com.manage.ecrewManage.domain.Join;
import com.manage.ecrewManage.domain.Manager;
import com.manage.ecrewManage.domain.PageBean;
import com.manage.ecrewManage.domain.User;
import com.manage.ecrewManage.service.FriendInfoService;
import com.manage.ecrewManage.service.IndexInfoService;
import com.manage.ecrewManage.service.impl.FriendInfoServiceImpl;
import com.manage.ecrewManage.service.impl.IndexInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/friendInfoByPageServlet")
public class FriendInfoByPageServlet extends HttpServlet {
    //service是公用的，因此直接放在最外面
    private FriendInfoService friendInfoService = new FriendInfoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从另外一个页面上接收manager的信息，
        Manager manager = (Manager) request.getAttribute("manager");
        //从页面上接收相关参数currentPage和pageSize
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //判断接收的两个参数是否为空
        if (currentPage == null || currentPage.length() == 0){
            currentPage = "1";
        }
        if (pageSize == null || pageSize.length() == 0){
            pageSize = "5";
        }

        //从页面上获取用来进行搜索的参数
        Map<String, String[]> condition = request.getParameterMap();
        for (String s : condition.keySet()) {
            System.out.println("s = " + s);
            System.out.println(condition.get(s)[0]);
        }
        //调用service查询user，返回的是一个pageBean
        PageBean<Join> pageBean = friendInfoService.selectUserByPage(currentPage,pageSize,condition);
        //将查询出来的manager保存在PageBean中
        //将PageBean保存起来，跳转到index.jsp
        request.getSession().setAttribute("pageBean",pageBean);
//        request.setAttribute("pageBean",pageBean);
        //将页面上接收的查询条件的参数也保存在session中，方便回显
//        request.getSession().setAttribute("condition",condition);
        request.setAttribute("condition",condition);
        request.getRequestDispatcher("/friend.jsp").forward(request,response);
//        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
