package com.item.ecrew.service;

import com.item.ecrew.domain.*;

import java.util.List;

public interface IndexInfoService {
    /**
     * 获得网页上的一级分类的数据
     * @return
     */
    List<First> selectFirst();

    /**
     * 获取网页上的二级分类
     * @return
     */

    List<Second> selectSecond(int sid);

    /**
     * 获取网页上三级分类的数据
     * @param tid
     * @return
     */

    List<Third> selectThird(int tid);

    /**
     * 查询商品信息
     * @param gid
     * @return
     */
    List<Commodity> selectCommodity(int gid);

    /**
     * 查询商品的图片
     * @param pid
     * @return
     */
    List<Pic> selectImg(int pid);

    /**
     * 分页查询展示页面上的商品
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Commodity> selectCommodityByPage(int gid ,String currentPage, String pageSize);

    /**
     * 根据用户的输入，查询商品
     * @param gid
     * @param currentPage
     * @param pageSize
     * @param search
     * @return
     */
    PageBean<Commodity> search(int gid, String currentPage, String pageSize, String search);
}
