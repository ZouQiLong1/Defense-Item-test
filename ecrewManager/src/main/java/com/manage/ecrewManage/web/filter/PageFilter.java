package com.manage.ecrewManage.web.filter;//package com.item.ecrew.web.filter;
//
//import com.item.ecrew.domain.User;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class PageFilter implements Filter {
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        //1.将监听器中的request对象转为web的HttpServletRequest
//        HttpServletRequest req = (HttpServletRequest) request;
//        //2.拿到页面上的访问路径
//        String requestURI = req.getRequestURI();
//        //如果访问路径中包含着首页、登录、注册的路径，就放行
//        if (requestURI.contains("/index.jsp") || requestURI.contains("*.js") || requestURI.contains("*.css") || requestURI.contains("/images")
//                || requestURI.contains("/bootstrap") || requestURI.contains("/UserServlet") || requestURI.contains("/login.html") || requestURI.contains("/register.html")
//                || requestURI.contains("/BaseServlet") || requestURI.contains("/indexInfo")) {
//            chain.doFilter(request, response);
//        } else { //需要判断当前网页中是否存在一个user的session，如果有，就代表用户已经登录
//            User user = (User) req.getSession().getAttribute("user");
//            if (user != null ) {
//                //存在这个session，代表用户已经登录，直接放行
//                chain.doFilter(request,response);
//            } else {
//                //剩下的情况就是用户需要登陆的 情况，跳转到登录页
//                req.getRequestDispatcher(req.getContextPath() + "/login.html").forward(req,(HttpServletResponse)response);
//            }
//
//        }
//    }
//}
//
