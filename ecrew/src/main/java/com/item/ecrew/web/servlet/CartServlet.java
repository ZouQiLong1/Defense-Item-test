package com.item.ecrew.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.ecrew.domain.*;
import com.item.ecrew.service.CartService;
import com.item.ecrew.service.IndexInfoService;
import com.item.ecrew.service.impl.CartServiceImpl;
import com.item.ecrew.service.impl.IndexInfoServiceImpl;
import com.item.ecrew.utils.ObjectMapperUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {
    //因为service层在很多地方都是公用的,因此我们直接放在最外层
    private CartService cartService = new CartServiceImpl();
    private IndexInfoService indexInfoService = new IndexInfoServiceImpl();
    //查询购物车信息
    public void cartInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取user对象,使用user的id作为参数查询购物车数据
        User user = (User) request.getSession().getAttribute("user");
        //创建一个处理错误信息的对象,接收servlet中的错误信息
        List<Cart> carts = null;
        //判断session中有没有user对象
        if (user != null){
            //直接调用service层,拿到cart中的数据
            carts = cartService.selectInfo(user.getId());
            //查出来的 cart中不包含商品信息,因此需要根据cart中的goodsId,查询商品信息
            for (Cart cart : carts) {
                List<Commodity> commodities = indexInfoService.selectCommodity(cart.getGoodsId());
                //因为我们知道这里查出来的commodity是只有一条数据的,所以我们可以直接使用commodities.get(0)
                //查出商品信息之后,还需要将商品对应的图片页放在其中
                //接下来根据商品id,查询图片数组
                List<Pic> pics = indexInfoService.selectImg(commodities.get(0).getId());
                //将查询出来的图片放到Commodity对象中
                commodities.get(0).setPics(pics);
                //当所有的数据都准备完毕之后,将商品信息返回给cart,然后返回在页面上
                cart.setCommodity(commodities.get(0));


            }
        }
        //将查询出来的cart返回出去,用来渲染界面
        response.setContentType("application/json,charset=utf-8");
        ObjectMapperUtil.writeValue(carts,response);
    }

    //删除购物车信息
    public void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面上获取参数，商品id
        String goodsId = request.getParameter("goodsId");
        //从session中获取user对象,使用user的id作为参数查询购物车数据
        User user = (User) request.getSession().getAttribute("user");
        //创建一个order，用来保存userid和goodsid
        Cart cart = new Cart();
        //创建一个处理错误信息的对象，给页面上返回信息，
        ResultInfo resultInfo = new ResultInfo();
        //判断session中有没有user对象
        if (user != null){
            //将user中的userid装到order中
            cart.setUserId(user.getId());
            //将页面上接收的参数goodsId存放到order中
            cart.setGoodsId(Integer.parseInt(goodsId));
            //调用service，删除购物车数据
            boolean flag = cartService.deleteCart(cart);
            //如果返回false，表示删除失败，如果返回true，表示删除成功
            if (flag){
                resultInfo.setFlag(true);
                resultInfo.setMsg("删除成功");
            } else {
                resultInfo.setFlag(false);
                resultInfo.setMsg("删除失败");
            }
        } else {
            //user不存在的话，就是未登录，我们也进行提示
            resultInfo.setMsg("您还未登录，请您先进行登录");
            resultInfo.setFlag(false);
        }
        //将查询出来的cart返回出去,用来渲染界面
        response.setContentType("application/json,charset=utf-8");
        ObjectMapperUtil.writeValue(resultInfo,response);
    }

    //查询订单信息
    public void orderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取user对象,使用user的id作为参数查询购物车数据
        User user = (User) request.getSession().getAttribute("user");
        //创建一个处理错误信息的对象,接收servlet中的错误信息
        List<Order> orders = null;
        //判断session中有没有user对象
        if (user != null){
            //直接调用service层,拿到cart中的数据
            orders = cartService.selectOrderInfo(user.getId());
            //查出来的 cart中不包含商品信息,因此需要根据cart中的goodsId,查询商品信息
            for (Order order : orders) {
                List<Commodity> commodities = indexInfoService.selectCommodity(order.getGoodsId());
                //因为我们知道这里查出来的commodity是只有一条数据的,所以我们可以直接使用commodities.get(0)
                //查出商品信息之后,还需要将商品对应的图片页放在其中
                //接下来根据商品id,查询图片数组
                List<Pic> pics = indexInfoService.selectImg(commodities.get(0).getId());
                //将查询出来的图片放到Commodity对象中
                commodities.get(0).setPics(pics);
                //当所有的数据都准备完毕之后,将商品信息返回给cart,然后返回在页面上
                order.setCommodity(commodities.get(0));
            }
        }
        //将查询出来的cart返回出去,用来渲染界面
        response.setContentType("application/json,charset=utf-8");
        ObjectMapperUtil.writeValue(orders,response);
    }

    public void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先，从页面上接收参数，也就是需要删除的商品的id，
        //因为我们 在页面上接收的参数是一个数组，因此要使用request.getParameterValues
        String[] goodsIds = request.getParameterValues("goodsId");
        //从session中获取用户的session,已经才知道session是一个对象，因此直接强转
        User user = (User) request.getSession().getAttribute("user");
        //判断user是否为空
        if (user != null){
            //如果user不为空的话，就再来判断接收的参数是否为空，
            if (goodsIds != null && goodsIds.length > 0){
                //循环遍历参数，然后根据商品id删除购物车
                for (String goodsId : goodsIds) {
                    //创建一个Cart对象，用来保存userid和goodsId
                    Cart cart = new Cart();
                    cart.setUserId(user.getId()); //保存userid
                    cart.setGoodsId(Integer.parseInt(goodsId)); //保存goodsId
                    //调用service层，删除购物车数据
                    cartService.deleteCart(cart);
                    //再次调用service层，将数据保存在订单列表里面
                    cartService.addToOrder(cart);
                }
                //将所有的数据添加完成之后，然后重定向到指定的页面
                response.sendRedirect("http://localhost/loading01.html");
            }
            else {
                //如果页面上接收的参数为空。直接重定向
                response.sendRedirect("http://localhost/cart.html");
            }
        }else {
            //user为空的话我们不进行任何操作
            //目前用户没有登录，就跳转到登录页面
            response.sendRedirect("http://localhost/login.html");
        }
    }

}
