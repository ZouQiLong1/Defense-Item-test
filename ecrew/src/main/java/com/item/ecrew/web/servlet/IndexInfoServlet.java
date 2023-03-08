package com.item.ecrew.web.servlet;

import com.item.ecrew.domain.*;
import com.item.ecrew.service.IndexInfoService;
import com.item.ecrew.service.impl.IndexInfoServiceImpl;
import com.item.ecrew.utils.ObjectMapperUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/indexInfo/*")
public class IndexInfoServlet extends BaseServlet {
//    service层是我们经常使用的，因此我们可以直接将service对象放在最外面
    private IndexInfoService indexInfoService =  new IndexInfoServiceImpl();

    public void firstSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //第一个分栏，没有需要传递的参数，直接获取
        List<First> first = indexInfoService.selectFirst();
        //将查询出来的参数，返回给页面，在页面上进行数据展示
        ObjectMapperUtil.writeValue(first,response);
    }
    public void secondSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先从页面上获取参数，标识当前页面是属于哪个分类的
        String sid1 = request.getParameter("sid");
        //在传递参数的时候，需要判断sid是否为空，
        int sid;
        if (sid1 != null){
            sid = Integer.parseInt(sid1);
        } else {
            sid = 1;
        }
        //第二个分栏，需要第一个传递的参数来判断二级分栏是属于哪个板块的
        List<Second> seconds = indexInfoService.selectSecond(sid);
        //将查询出来的参数，返回给页面，在页面上进行数据展示
        ObjectMapperUtil.writeValue(seconds,response);
    }
    public void thirdSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先从页面上获取参数，标识当前页面是属于哪个分类的
        String tid1 = request.getParameter("tid");
        //在传递参数的时候，需要判断sid是否为空，
        int tid;
        if (tid1 != null){
            tid = Integer.parseInt(tid1);
        } else {
            tid = 1;
        }
        //第二个分栏，需要第一个传递的参数来判断二级分栏是属于哪个板块的
        List<Third> thirds = indexInfoService.selectThird(tid);
        //将查询出来的参数，返回给页面，在页面上进行数据展示
        ObjectMapperUtil.writeValue(thirds,response);
    }
    public void commodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先从页面上获取参数，标识当前商品是属于哪个分类的
        String gid1 = request.getParameter("gid");
        //在传递参数的时候，需要判断sid是否为空，
        int gid;
        if (gid1 != null){
            gid = Integer.parseInt(gid1);
        } else {
            gid = 1;
        }
        //第二个分栏，需要第一个传递的参数来判断二级分栏是属于哪个板块的
        List<Commodity> commodities = indexInfoService.selectCommodity(gid);
        //查出商品之后，还需要根据商品的id查出商品对应的图片，然后将图片放入到commdity对象中
        for (Commodity commodity : commodities) {
            //根据每条商品的数据，查出图片
            List<Pic> pics = indexInfoService.selectImg(commodity.getId());
            //将查询出来的结果，返回在商品对象中
            commodity.setPics(pics);
        }
        //将查询出来的参数，返回给页面，在页面上进行数据展示
        ObjectMapperUtil.writeValue(commodities,response);
    }
    public void indexPagination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先从页面上获取参数，标识当前商品是属于哪个分类的
        String gid1 = request.getParameter("gid");
        //在传递参数的时候，需要判断sid是否为空，
        int gid;
        if (gid1 != null && gid1.length() > 0){
            gid = Integer.parseInt(gid1);
        } else {
            gid = 1;
        }
        //拿到商品的当前页和每页展示数量
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //在传递参数的时候，需要判断currentPage和pageSize是否为空，
        if (currentPage == null || currentPage.length() == 0){
            currentPage = "1";
        }
        if (pageSize == null || pageSize.length() == 0){
            pageSize = "5";
        }
        //调用service，查询页面上展示的数据
        PageBean<Commodity> pageBean = indexInfoService.selectCommodityByPage(gid,currentPage,pageSize);

        //将查询出来的参数，返回给页面，在页面上进行数据展示
        ObjectMapperUtil.writeValue(pageBean,response);
    }
    //添加查询功能
    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //因为接收的参数中存在中文，因此，需要设置编码
        request.setCharacterEncoding("utf-8");
        //先从页面上获取参数，标识当前商品是属于哪个分类的
        String gid1 = request.getParameter("gid");
        //在传递参数的时候，需要判断sid是否为空，
        int gid;
        if (gid1 != null && gid1.length() > 0){
            gid = Integer.parseInt(gid1);
        } else {
            gid = 1;
        }
        //拿到商品的当前页和每页展示数量
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //在传递参数的时候，需要判断currentPage和pageSize是否为空，
        if (currentPage == null || currentPage.length() == 0){
            currentPage = "1";
        }
        if (pageSize == null || pageSize.length() == 0){
            pageSize = "5";
        }
        //先从页面上获取参数
        String search = request.getParameter("search");
        //需要判断页面上的参数search是否为空，如果为空的话，就不传递这个search参数
        //创建一个用来保存商品信息的数组
        PageBean<Commodity> commodityPageBean = new PageBean<Commodity>();
        if (search == null || search.length() == 0){
            commodityPageBean = indexInfoService.selectCommodityByPage(gid,currentPage,pageSize);
        } else {
        //根据search，调用service层查询数据，返回一个list集合，展示在页面上
            commodityPageBean = indexInfoService.search(gid,currentPage,pageSize,search);
        }
        //创建一个处理错误信息的类
        ResultInfo resultInfo = new ResultInfo();
        //将从数据库查询出来的 商品信息返回给页面上，让前端页面来进行判断
            ObjectMapperUtil.writeValue(commodityPageBean,response);
    }
}
