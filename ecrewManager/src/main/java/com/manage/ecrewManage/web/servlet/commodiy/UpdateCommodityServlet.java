package com.manage.ecrewManage.web.servlet.commodiy;

import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.domain.Manager;
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

@WebServlet("/updateCommodityServlet")
public class UpdateCommodityServlet extends HttpServlet {
    //因为service是公用的,因此我们直接将他放在最外面
    private UpdateCommodityService updateCommodityService = new UpdateCommodityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先,先从页面上接收参数,每个账号的id
        String id = request.getParameter("id");
        //因为注册页面有关于登录者的信息,因此我们也需要将这些信息拿过来放在一个manager对象中,
        //当我们修改完毕之后才能将manager的信息放在页面上
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建一个manager对象,用来保存manager的数据
        Manager manager = new Manager();
        try {
            BeanUtils.populate(manager,parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //为了防止页面上出现错误,做一下优化,判断一下页面上id是否为空
        if (id == null && id.length() == 0){
            //如果参数为空,就重新跳回到那个页面
            request.getRequestDispatcher("/commodity.jsp").forward(request,response);
        } else {
            //根据页面上接收的用户id,调用service从数据库中查询这个user对象
            Commodity commodity = updateCommodityService.selectUser(Integer.parseInt(id));
            //将user对象保存在session域对象中,重定向到update.html
            request.getSession().setAttribute("commodity",commodity);
            response.sendRedirect(request.getContextPath() + "/updateCommodity.jsp");
            //最后在jsp页面上将数据回显出来
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
