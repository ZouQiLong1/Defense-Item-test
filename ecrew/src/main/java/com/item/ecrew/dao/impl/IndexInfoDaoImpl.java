package com.item.ecrew.dao.impl;

import com.item.ecrew.dao.IndexInfoDao;
import com.item.ecrew.domain.*;

import java.util.List;

public class IndexInfoDaoImpl implements IndexInfoDao {
    /**
     * 查询一级分类的所有数据
     * @return
     */
    @Override
    public List<First> selectFirst() {
        String sql = "select * from first";
        List<First> firstList = BasicDao.selectMultiply(sql, First.class);
        return firstList;
    }

    @Override
    public List<Second> selectSecond(int sid) {
        String sql = "select * from second where sid = ?";
        List<Second> seconds = BasicDao.selectMultiply(sql, Second.class, sid);
        return seconds;
    }

    @Override
    public List<Third> selectThird(int tid) {
        String sql = "select * from third where tid = ?";
        List<Third> thirds = BasicDao.selectMultiply(sql, Third.class, tid);
        return thirds;
    }

    @Override
    public List<Commodity> selectCommodity(int gid) {
        String sql = "select * from commodity where gid = ?";
        List<Commodity> commodities = BasicDao.selectMultiply(sql, Commodity.class, gid);
        return commodities;
    }

    @Override
    public List<Pic> selectImg(int nid) {
        String sql = "select * from img where nid = ?";
        List<Pic> pics = BasicDao.selectMultiply(sql, Pic.class, nid);
        return pics;
    }

    @Override
    public int selectTotalCunt(int gid) {
        String sql = "select count(*) from commodity where gid = ?";
        int totalCount = Integer.parseInt(BasicDao.selectSalary(sql, gid).toString());
        return totalCount;
    }

    @Override
    public List<Commodity> selectList(int gid, int start, int pageSize) {
        String sql = "select * from commodity where gid = ? limit ?,?";
        List<Commodity> commodities = BasicDao.selectMultiply(sql, Commodity.class, gid, start, pageSize);
        return commodities;
    }

    /**
     * 从数据库中查出符合条件的总数
     * @param gid
     * @param search
     * @return
     */
    @Override
    public int selectTotalCuntBySearch(int gid, String search) {
        String search1 = "%" +search+ "%";
        String sql = "select count(*) from commodity where gid = ? and title like ?";
        int totalCount = Integer.parseInt(BasicDao.selectSalary(sql, gid, search1).toString());
        return totalCount;
    }

    /**
     * 根据条件查询商品
     * @param gid
     * @param start
     * @param pageSize
     * @param search
     * @return
     */
    @Override
    public List<Commodity> selectListBySearch(int gid, int start, int pageSize, String search) {
        String search1 = "%" +search+ "%";
        String sql = "select * from commodity where gid = ? and title like ? limit ?,?";
        List<Commodity> commodities = BasicDao.selectMultiply(sql, Commodity.class, gid, search1, start, pageSize);
        return commodities;
    }
}
