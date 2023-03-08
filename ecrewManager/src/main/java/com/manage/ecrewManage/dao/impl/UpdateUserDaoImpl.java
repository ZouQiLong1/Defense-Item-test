package com.manage.ecrewManage.dao.impl;

import com.manage.ecrewManage.dao.UpdateUserDao;
import com.manage.ecrewManage.domain.User;

public class UpdateUserDaoImpl implements UpdateUserDao {
    /**
     * 根据id查询user对象
     * @param id
     * @return
     */
    @Override
    public User selectUser(int id) {
        String sql = "select * from user where id = ?";
        User user = BasicDao.selectSingle(sql, User.class, id);
        return user;
    }

    /**
     * 根据userid,修改用户信息
     * @param id
     * @param password
     * @param status
     */
    @Override
    public void updateUser(int id, String password, String status) {
        String sql = "update user set password = ? , status = ? where id = ?";
        BasicDao.update(sql,password,status,id);
    }
    /**
     * 根据id删除数据库中的user
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        //因为user表有一个外键对应的是cart表，因此需要先删除cart表中对应的数据
        String sql1 = "delete from cart where userid = ?";
        BasicDao.update(sql1,id);
        //删除完cart表中的记录，再来删除user表中的记录
        String sql = "delete from user where id = ?";
        BasicDao.update(sql,id);
    }
    /**
     * 新增用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        String sql = "insert into user values(?,?,?,?,?,?,?)";
        BasicDao.update(sql,null,user.getName(),user.getUsername(),user.getPassword(),user.getStatus(),"12318",user.getEmail());
    }
    /**
     * 根据账号查询user
     * @param username
     * @return
     */
    @Override
    public User selectUserByUsername(String username) {
        String sql = "select * from user where username = ?";
        User user = BasicDao.selectSingle(sql, User.class, username);
        return user;
    }
}
