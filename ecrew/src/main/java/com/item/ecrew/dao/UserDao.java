package com.item.ecrew.dao;

import com.item.ecrew.domain.User;

public interface UserDao {
    /**
     * 这个方法是查询，数据库中是否有user
     * @param user
     * @return
     */
    User selectUser(User user);

    /**
     * 这个方法是注册用户，也就是插入数据
     * @param user
     */
    void register(User user);

    /**
     *根据code查询user
     * @param code
     * @return
     */
    User selectUserByCode(String code);

    /**
     * 修改用户的可用状态
     * @param code
     */
    void activeUser(String code);

    /**
     * 用户登录的方法
     * @param user
     * @return
     */
    User login(User user);
}
