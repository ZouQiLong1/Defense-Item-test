package com.item.ecrew.dao;

import com.item.ecrew.domain.*;

import java.util.List;

public interface IndexInfoDao {
    /**
     * 查询一级分类的所有数据
     * @return
     */
    List<First> selectFirst();

    /**
     * 查询二级分类的所有数据
     * @param sid
     * @return
     */

    List<Second> selectSecond(int sid);

    /**
     * 获取页面上的三级分类的数据
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
     * 根据商品id查询商品的图片
     * @param pid
     * @return
     */
    List<Pic> selectImg(int pid);

    /**
     * 查询总数居条数
     * @param gid
     * @return
     */
    int selectTotalCunt(int gid);

    /**
     * 根据起始和结束，还有标识，查询出list集合
     * @param gid
     * @param start
     * @param pageSize
     * @return
     */
    List<Commodity> selectList(int gid, int start, int pageSize);

    /**
     * 从数据库中查出符合条件的总数
     * @param gid
     * @param search
     * @return
     */
    int selectTotalCuntBySearch(int gid, String search);

    /**
     * 根据条件查询商品
     * @param gid
     * @param start
     * @param pageSize
     * @param search
     * @return
     */
    List<Commodity> selectListBySearch(int gid, int start, int pageSize, String search);
}
