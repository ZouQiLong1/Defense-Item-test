package com.item.ecrew.service.impl;

import com.item.ecrew.dao.IndexInfoDao;
import com.item.ecrew.dao.impl.BasicDao;
import com.item.ecrew.dao.impl.IndexInfoDaoImpl;
import com.item.ecrew.domain.*;
import com.item.ecrew.service.IndexInfoService;
import com.item.ecrew.utils.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IndexInfoServiceImpl implements IndexInfoService {
    //dao层中的对象，是我们公用的，因此我们直接同意new出来
    private IndexInfoDao indexInfo = new IndexInfoDaoImpl();
    /**
     * 获得网页上的一级分类的数据
     * @return
     */
    @Override
    public List<First> selectFirst() {
        //因为这个一级分类的数据是不太会发生改变的，因此可以将他存在redis中
        //1.先查看redis中存不存在这个First数组
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> first1 = jedis.zrangeWithScores("first", 0, -1);
        if (first1 != null && first1.size() > 0){
            //如果查询出来的first不为空，就说明redis中存在数据
            //但是查询出来的数据不是First对象的数组，因此需要转换一下
            List<First> firsts = new ArrayList<>();
            for (Tuple tuple : first1) {
                First first = new First(); //创建一个First对象
                //给这个First对象设置值
                first.setId((int)tuple.getScore());
                first.setName(tuple.getElement());
                //最后，再将我们new出来的First对象放到First对象的数组中
                firsts.add(first);
            }
            System.out.println("redis中取出数据");
            JedisUtil.close(jedis);
            return firsts;
        } else {
            //redis中没有数据，在数据库中查询数据，并且将数据保存在redis中
            List<First> first = indexInfo.selectFirst();
            for (First first2 : first) {
                //将数据保存在redis中
                jedis.zadd("first",first2.getId(),first2.getName());
            }
            System.out.println("mysql中取数据");
            JedisUtil.close(jedis);
            return first;
        }

    }

    /**
     * 获取网页上二级分类的数据
     * @param sid
     * @return
     */
    @Override
    public List<Second> selectSecond(int sid) {
        List<Second> list = indexInfo.selectSecond(sid);
        return list;
    }

    /**
     * 获取页面上三级分类
     * @param tid
     * @return
     */
    @Override
    public List<Third> selectThird(int tid) {
        List<Third> thirds = indexInfo.selectThird(tid);
        return thirds;
    }

    /**
     * 查询商品信息
     * @param gid
     * @return
     */
    @Override
    public List<Commodity> selectCommodity(int gid) {
        List<Commodity> commodities = indexInfo.selectCommodity(gid);
        return commodities;
    }

    /**
     * 根据商品id查询商品的图片
     * @param pid
     * @return
     */
    @Override
    public List<Pic> selectImg(int pid) {
        List<Pic> pics = indexInfo.selectImg(pid);
        return pics;
    }
    /**
     * 分页查询展示页面上的商品
     * @param _currentPage
     * @param _pageSize
     * @return
     */
    @Override
    public PageBean<Commodity> selectCommodityByPage(int gid,String _currentPage, String _pageSize) {
        //将这两个参数转为int
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Commodity> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //从数据库中查询出总的条数
        int totalCount = indexInfo.selectTotalCunt(gid);
        //将总条数设到pageBean中
        pageBean.setTotalCount(totalCount);
        //计算总页面数
        int totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
        //将总页面数，保存到pageBean中
        pageBean.setTotalPage(totalPage);
        //计算查询的list集合的起始页
        int start = (currentPage - 1) * pageSize;
        //从数据库中查询出首页数据，list集合
        List<Commodity> list = indexInfo.selectList(gid,start,pageSize);
        for (Commodity commodity : list) {
            //从数据库查出来每条商品数据对应的图片
            List<Pic> pics = indexInfo.selectImg(commodity.getId());
            commodity.setPics(pics);
        }
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 根据用户的输入，查询商品
     * @param gid
     * @param _currentPage
     * @param _pageSize
     * @param search
     * @return
     */
    @Override
    public PageBean<Commodity> search(int gid, String _currentPage, String _pageSize, String search) {
        //将这两个参数转为int
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Commodity> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //从数据库中查询出总的条数
        int totalCount = indexInfo.selectTotalCuntBySearch(gid,search);
        //将总条数设到pageBean中
        pageBean.setTotalCount(totalCount);
        //计算总页面数
        int totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
        //将总页面数，保存到pageBean中
        pageBean.setTotalPage(totalPage);
        //计算查询的list集合的起始页
        int start = (currentPage - 1) * pageSize;
        //从数据库中查询出首页数据，list集合
        List<Commodity> list = indexInfo.selectListBySearch(gid,start,pageSize,search);
        for (Commodity commodity : list) {
            //从数据库查出来每条商品数据对应的图片
            List<Pic> pics = indexInfo.selectImg(commodity.getId());
            commodity.setPics(pics);
        }
        pageBean.setList(list);
        return pageBean;
    }


}
