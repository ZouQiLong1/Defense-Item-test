package com.item.ecrew.web.servlet;

import com.item.ecrew.domain.Join;
import com.item.ecrew.domain.ResultInfo;
import com.item.ecrew.service.JoinService;
import com.item.ecrew.service.impl.JoinServiceImpl;
import com.item.ecrew.utils.ObjectMapperUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/join/*")
public class JoinServlet extends BaseServlet {
    //JoinService是公用的，因此我们直接放在外面
    private JoinService joinService = new JoinServiceImpl();
    public void joinUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先从页面上接收加盟者的信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建一个Join对象，用来存储参数
        Join join = new Join();
        try {
            BeanUtils.populate(join,parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //调用service层，插入数据
        boolean flag = joinService.join(join);
        ResultInfo resultInfo = new ResultInfo();
        response.setContentType("application/json,charset=utf-8");
        if (flag){
            resultInfo.setFlag(true);
            resultInfo.setMsg("信息提交成功");
        } else {
            resultInfo.setFlag(false);
            resultInfo.setMsg("用户已存在，请勿重复提交");
        }
        //将相应结果的对象，返回到页面上
        ObjectMapperUtil.writeValue(resultInfo,response);
    }
}
