package com.manage.ecrewManage.dao;

import com.manage.ecrewManage.domain.User;

public interface UpdateUserDao {
    /**
     * 根据id查询user对象
     * @param id
     * @return
     */
    User selectUser(int id);

    /**
     * 根据userid,修改用户信息
     * @param id
     * @param password
     * @param status
     */
    void updateUser(int id, String password, String status);
    /**
     * 根据id删除数据库中的user
     * @param id
     */
    void deleteUser(int id);
    /**
     * 新增用户
     * @param user
     */
    void addUser(User user);

    /**
     * 根据账号查询user
     * @param username
     * @return
     */
    User selectUserByUsername(String username);
}
