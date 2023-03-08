package com.item.ecrew.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //拿到页面请求的路径
        String requestURI = req.getRequestURI();
        //截取路径，拿到路径访问的方法名字,截取字符串是前闭后开的，因此需要+1
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        //通过方法名，利用反射来获得真正的方法,这个地方的this就是调用者，我们就通过这个调用者拿到方法
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        //拿到方法之后，使用反射来执行方法
        try {
            method.invoke(this,req,resp);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
