package com.manage.ecrewManage.web.servlet.user;

import com.manage.ecrewManage.domain.Manager;
import com.manage.ecrewManage.domain.User;
import com.manage.ecrewManage.service.impl.IndexInfoServiceImpl;
import com.manage.ecrewManage.service.IndexInfoService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/indexInfoServlet")
public class IndexInfoServlet extends HttpServlet {
    //因为service对象是公用的，因此可以放在外面
    private IndexInfoService indexInfoService = new IndexInfoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取从另外一个页面上传递过来的对象
        Manager manager1 = (Manager) request.getAttribute("manager");
        //2.创建一个用来保存首页信息的对象,user对象,
        // 这个地方的user对象和首页上的user对象不一样，因为这个user中还要保存登陆着的信息
        List<User> users = new ArrayList<User>();
        //3.调用service层来查询indexInfo的信息,返回的是一个user对象
        users = indexInfoService.selectUser();
        //4.将域对象中取出来的manager保存在user中，然后在重定向到首页
        //因为这里查询出来的user对象有很多个，但是页面上只需要保存一个manager对象就行，
        // 因此，我们把manager对象保存在第一个user中，取的时候我们也只取第一个user对象中的manager
        User user = users.get(0);
        user.setManager(manager1);
        //将查询出来的users集合保存在域对象中，
        //这里我们要跳转到一个新的页面，使用重定向，因此使用session保存数据
        request.getSession().setAttribute("users",users);
        //5.重定向到首页
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
