package com.item.ecrew.web.servlet;

import com.item.ecrew.domain.*;
import com.item.ecrew.service.GoodsService;
import com.item.ecrew.service.IndexInfoService;
import com.item.ecrew.service.impl.GoodsServiceImpl;
import com.item.ecrew.service.impl.IndexInfoServiceImpl;
import com.item.ecrew.utils.ObjectMapperUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/goods/*")
public class GoodsServlet extends BaseServlet {
    //service是整个页面都使用的，因此我们可以直接将它提取在最外面
    private GoodsService goodsService = new GoodsServiceImpl();
    private IndexInfoService indexInfoService = new IndexInfoServiceImpl();
    //展示页面信息
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面上，接收商品的参数id，用于专门标识商品
        String id = request.getParameter("id");
        if (id == null || id.length() == 0){
            id = "1";
        }
        //调用service层，查询数据库,查出商品id对应的商品信息
        Commodity commodity  = goodsService.selectGoods(id);
        //根据商品信息中的id，查出每条商品对应的图片
        List<Pic> pics = indexInfoService.selectImg(commodity.getId());
        //将查询出来的图片，放入到商品对象中，进行展示
        commodity.setPics(pics);
        //将取出来的商品信息，返回给界面
        response.setContentType("application/json,charset=utf-8");
        ObjectMapperUtil.writeValue(commodity,response);
    }
    //添加到购物车
    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面上，接收商品的参数id，用于专门标识商品
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建一个Cart对象，用来保存页面接收的数据
        Cart cart = new Cart();
        try {
            BeanUtils.populate(cart,parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //获取session中保存的user
        User user = (User) request.getSession().getAttribute("user");

        //创建一个ResultInfo对象,来保存错误信息
        ResultInfo resultInfo = new ResultInfo();

        //判断session中是否存在user
        if (user != null){
            //如果user对象不为空，将user的标识id保存在Cart中
            cart.setUserId(user.getId());
            //调用service层,查询是否有这个购物车,如果有就数量+number,如果没有就插入新数据
            goodsService.addToCart(cart);
            resultInfo.setFlag(true);
            resultInfo.setMsg("添加成功");
        } else {
            resultInfo.setFlag(false);
            resultInfo.setMsg("您还未登录,请先登录");
        }
        response.setContentType("text/html,charset=utf-8");
        ObjectMapperUtil.writeValue(resultInfo,response);
    }

    //减少购物车数量
    public void descToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面上，接收商品的参数id，用于专门标识商品
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建一个Cart对象，用来保存页面接收的数据
        Cart cart = new Cart();
        try {
            BeanUtils.populate(cart,parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //获取session中保存的user
        User user = (User) request.getSession().getAttribute("user");

        //判断session中是否存在user
        if (user != null){
            //如果user对象不为空，将user的标识id保存在Cart中
            cart.setUserId(user.getId());
            //调用service层,查询是否有这个购物车,如果有就数量+number,如果没有就插入新数据
            goodsService.descToCart(cart);
        }
        //不需要往页面上输出
    }

    //添加到订单
    public void addToOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面上，接收商品的参数id，用于专门标识商品
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建一个Cart对象，用来保存页面接收的数据
        Order order = new Order();
        try {
            BeanUtils.populate(order,parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //获取session中保存的user
        User user = (User) request.getSession().getAttribute("user");

        //创建一个ResultInfo对象,来保存错误信息
        ResultInfo resultInfo = new ResultInfo();
        //判断session中是否存在user
        if (user != null){
            //如果user对象不为空，将user的标识id保存在Cart中
            order.setUserId(user.getId());
            //调用service层,查询是否有这个购物车,如果有就数量+number,如果没有就插入新数据
            goodsService.addToOrder(order);
            resultInfo.setFlag(true);
            resultInfo.setMsg("购买成功");
        } else {
            resultInfo.setFlag(false);
            resultInfo.setMsg("您还未登录,请先登录");
        }
        response.setContentType("text/html,charset=utf-8");
        ObjectMapperUtil.writeValue(resultInfo,response);
    }
}
