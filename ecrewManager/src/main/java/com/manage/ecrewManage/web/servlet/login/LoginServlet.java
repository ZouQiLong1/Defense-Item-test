package com.manage.ecrewManage.web.servlet.login;

import com.manage.ecrewManage.domain.Manager;
import com.manage.ecrewManage.service.LoginService;
import com.manage.ecrewManage.service.impl.LoginServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    //因为service层是我们公用的，因此我们可以把他放在最外面
    private LoginService loginService = new LoginServiceImpl();
    //用户进行登录的方法
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             //接受页面上的参数username，password，identity
            Map<String, String[]> parameterMap = request.getParameterMap();
            //创建一个manager对象来保存数据
            Manager manager1 = new Manager();
            //封装数据
            try {
                BeanUtils.populate(manager1,parameterMap);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            //调用service查询数据库中的数据，返回一个manager对象
            Manager manager = loginService.login(manager1);
            if (manager == null) {
                request.setAttribute("err_msg","账号或密码错误");
                //页面上保存数据的时候，我们需要使用转发
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            } else {
                //我们要跳转到另外一个servlrt，还不是页面上，因此我们不是永久保存数据，使用request域保存对象
//                request.getSession().setAttribute("manager",manager);
                request.setAttribute("manager",manager);
                //这里我们不能直接跳转到页面上，因为页面上没有数据，因此我们要在跳转查找数据的servlet之后，在跳转到页面上
//                response.sendRedirect(request.getContextPath() + "/indexInfoServlet");
                request.getRequestDispatcher("/indexInfoByPageServlet").forward(request,response);
            }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
