package com.manage.ecrewManage.web.servlet.commodiy;

import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.domain.User;
import com.manage.ecrewManage.service.UpdateCommodityService;
import com.manage.ecrewManage.service.UpdateUserService;
import com.manage.ecrewManage.service.impl.UpdateCommodityServiceImpl;
import com.manage.ecrewManage.service.impl.UpdateUserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateCommodityInfoServlet")
public class UpdateCommodityInfoServlet extends HttpServlet {
    //service是公用的,因此我们直接放在外面
    private UpdateCommodityService updateCommodityService = new UpdateCommodityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.首先,接收页面上传递过来的参数:当前用户id,密码和激活状态
        Map<String, String[]> parameterMap = request.getParameterMap();
        //接收页面上隐藏于保存的manager对象,用于跳转回页面的时候进行展示
//        String manager = request.getParameter("manager");
//        ObjectMapper objectMapper = new ObjectMapper();
//        Manager manager1 = objectMapper.readValue(manager, Manager.class);
        //创建一个user对象,用来保存页面上接收的参数
        Commodity commodity = new Commodity();
        try {
            BeanUtils.populate(commodity,parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //调用service层,将这两条信息修改到数据库中
        boolean flag = updateCommodityService.updateUser(commodity);
        //根据数据修改成功与否的flag,跳转页面
        if (flag){
            //修改成功
            //修改成功之后,还需要跳转到首页进行信息刷新,因此我们需要查询一个manager对象保存在request域中,
            //这样才能保证jsp页面不会报错
//            request.setAttribute("manager",manager1);
            response.sendRedirect(request.getContextPath() + "/goodsInfoByPageServlet");
            //response.sendRedirect(request.getContextPath() + "/indexInfoServlet");
        } else {
            request.getRequestDispatcher("/updateCommodity.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
