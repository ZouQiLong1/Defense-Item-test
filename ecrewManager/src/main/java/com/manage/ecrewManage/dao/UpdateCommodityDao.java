package com.manage.ecrewManage.dao;

import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.domain.User;

public interface UpdateCommodityDao {
    /**
     * 根据id查询user对象
     * @param id
     * @return
     */
    Commodity selectUser(int id);



    /**
     * 根据id删除数据库中的user
     * @param id
     */
    void deleteUser(int id ,int gid);
    /**
     * 新增用户
     * @param commodity
     */
    void addUser(Commodity commodity);

    /**
     * 根据账号查询user
     * @param title
     * @return
     */
    Commodity selectUserByUsername(String title);
    /**
     * 根据userid,修改用户信息
     * @param id
     * @param title
     * @param description
     */
    void updateUser(int id, String title, String description, String price);
}
