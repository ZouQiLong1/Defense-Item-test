package com.manage.ecrewManage.dao.impl;

import com.manage.ecrewManage.dao.UpdateCommodityDao;
import com.manage.ecrewManage.dao.UpdateUserDao;
import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.domain.User;

public class UpdateCommodityDaoImpl implements UpdateCommodityDao {
    /**
     * 根据id查询user对象
     * @param id
     * @return
     */
    @Override
    public Commodity selectUser(int id) {
        String sql = "select * from commodity where id = ?";
        Commodity commodity = BasicDao.selectSingle(sql, Commodity.class, id);
        return commodity;
    }

    /**
     * 根据userid,修改用户信息
     * @param id
     * @param title
     * @param description
     */
    @Override
    public void updateUser(int id, String title, String description, String price) {
        String sql = "update commodity set title = ? , description = ? , price = ? where id = ?";
        BasicDao.update(sql,title,description,price,id);
    }
    /**
     * 根据id删除数据库中的user
     * @param id
     */
    @Override
    public void deleteUser(int id ,int gid) {
        //因为user表有一个外键对应的是cart表，因此需要先删除cart表中对应的数据
        String sql1 = "delete from third where id = ?";
        BasicDao.update(sql1,gid);
        //删除完cart表中的记录，再来删除user表中的记录
        String sql = "delete from commodity where gid = ?";
        BasicDao.update(sql,id);
    }
    /**
     * 新增用户
     * @param commodity
     */
    @Override
    public void addUser(Commodity commodity) {
        String sql = "insert into commodity values(?,?,?,?,?,?)";
        BasicDao.update(sql,null,commodity.getTitle(),commodity.getDescription(),commodity.getPrice(),999,2);
    }
    /**
     * 根据账号查询user
     * @param title
     * @return
     */
    @Override
    public Commodity selectUserByUsername(String title) {
        String sql = "select * from commodity where title = ?";
        Commodity commodity = BasicDao.selectSingle(sql, Commodity.class, title);
        return commodity;
    }
}
