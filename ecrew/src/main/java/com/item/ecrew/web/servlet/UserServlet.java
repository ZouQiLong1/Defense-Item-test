package com.item.ecrew.web.servlet;

import com.item.ecrew.domain.Commodity;
import com.item.ecrew.domain.PageBean;
import com.item.ecrew.domain.ResultInfo;
import com.item.ecrew.domain.User;
import com.item.ecrew.service.UserService;
import com.item.ecrew.service.impl.UserServiceImpl;
import com.item.ecrew.utils.ObjectMapperUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //service是很多层都公用的，因此我们可以将他提取在外面
    private UserService userService = new UserServiceImpl();
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从checkCodeServlet中拿到验证码
        String checkcode_server = request.getSession().getAttribute("CHECKCODE_SERVER").toString();
        //从页面上接收code表单的参数，也就是用户输入的验证码
        String code = request.getParameter("code");
        //使用字符串的比较方法，忽略大小写，从而进行判断
        if (!checkcode_server.equalsIgnoreCase(code)) {
            //进入if循环的话也就是用户输入的验证码不正确
            ResultInfo resultInfo = new ResultInfo(); //用来保存错误信息的对象
            //因为这里的验证已经出现错误，也就是用户输入的信息不符，所以直接返回错误信息
                resultInfo.setFlag(false);
                resultInfo.setMsg("验证码错误");
            //将查询出来的参数，返回给页面，在页面上进行数据展示
            ObjectMapperUtil.writeValue(resultInfo, response);
        } else { //else就是说输入的验证码和页面上获取到的验证码一样
            //先从页面上获取参数，标识当前商品是属于哪个分类的
            Map<String, String[]> parameterMap = request.getParameterMap();
            //创建一个user对象，用来接收和保存数据
            User user = new User();
            try {
                BeanUtils.populate(user, parameterMap);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            //session的删除需要在用户填写的验证码都是正确的情况下才删除session
            //在注册用户之前，拿完这个session之后，立即删除，保证用户注册成功之后，这个验证码是一次性的
            request.getSession().removeAttribute("CHECKCODE_SERVER");

            //调用service，操作数据库
            boolean flag = userService.register(user);
            //对返回来的flag进行判断，如果为真，代表插入成功，如果为假，代表插入失败
            ResultInfo resultInfo = new ResultInfo(); //用来保存错误信息的对象
            if (flag) {
                resultInfo.setFlag(true);
                resultInfo.setMsg("注册成功");
            } else {
                resultInfo.setFlag(false);
                resultInfo.setMsg("用户名已存在");
            }
            //将查询出来的参数，返回给页面，在页面上进行数据展示
            ObjectMapperUtil.writeValue(resultInfo, response);
        }
    }
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先从页面上获取参数，标识当前商品是属于哪个分类的
        String code = request.getParameter("code");
        //调用service，操作数据库
        boolean flag = userService.activeUser(code);
        //对返回来的flag进行判断，如果为真，代表插入成功，如果为假，代表插入失败
        ResultInfo resultInfo = new ResultInfo(); //用来保存错误信息的对象
        response.setContentType("text/html;charset=utf-8");
        String msg = null;
        if (flag){
            msg = "激活成功，点击<a href=\"http://localhost/login.html\">登录</a>";
//            response.getWriter().write("激活成功，点击<a href=\"http://localhost/login.html\">登录</a>");
        } else {
            msg = "注册失败了，请联系管理员";
//            response.getWriter().write("注册失败了，请联系管理员");
        }
        response.getWriter().write(msg);
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先从页面上获取参数，标识当前商品是属于哪个分类的
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建一个user对象，用来保存数据
        User user = new User();
        //将页面上接收的数据，保存在user对象中
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //调用service，操作数据库
        User user1 = userService.login(user);
        //对返回来的flag进行判断，如果为真，代表插入成功，如果为假，代表插入失败
        ResultInfo resultInfo = new ResultInfo(); //用来保存错误信息的对象
        response.setContentType("text/html;charset=utf-8");
            if (user1 != null){
                if ("N".equals(user1.getStatus())){
                    resultInfo.setFlag(false);
                    resultInfo.setMsg("账号未激活");
                } else {
                    resultInfo.setFlag(true);
                    resultInfo.setMsg("登录成功");
                    request.getSession().setAttribute("user",user1);
                }
            } else {
                resultInfo.setFlag(false);
                resultInfo.setMsg("账号或密码不正确");
            }
        ObjectMapperUtil.writeValue(resultInfo,response);
    }
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //查看session，中有没有userId的session
        User user = (User) request.getSession().getAttribute("user");
        ResultInfo resultInfo = new ResultInfo(); //用来保存错误信息的对象
        response.setContentType("text/html;charset=utf-8");
        if (user != null){
          resultInfo.setFlag(true);
          resultInfo.setMsg(user.getName());
        } else {
            resultInfo.setFlag(false);
        }
        ObjectMapperUtil.writeValue(resultInfo,response);
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查看session，中有没有userId的session
        User user = (User) request.getSession().getAttribute("user");
        ResultInfo resultInfo = new ResultInfo(); //用来保存错误信息的对象
        response.setContentType("text/html;charset=utf-8");
        if (user != null){
            resultInfo.setFlag(true);
            request.getSession().removeAttribute("user");
            resultInfo.setMsg("退出成功");
        } else {
            resultInfo.setFlag(false);
        }
        ObjectMapperUtil.writeValue(resultInfo,response);
    }

}
