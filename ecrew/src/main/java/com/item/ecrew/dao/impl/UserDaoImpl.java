package com.item.ecrew.dao.impl;

import com.item.ecrew.dao.UserDao;
import com.item.ecrew.domain.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectUser(User user) {
        String sql = "select * from user where username = ?";
        User user1 = BasicDao.selectSingle(sql, User.class, user.getUsername());
        return user1;
    }

    @Override
    public void register(User user) {
        String sql = "insert into user values(?,?,?,?,?,?,?)";
        //再插入数据的时候，需要存入一个code码，这样的话才能在注册的时候验证是不是当前用户来注册的
        //再插入数据的时候，需要将可用状态设置为N，表示用户现在是不可用的
        BasicDao.update(sql,null,user.getName(),user.getUsername(),user.getPassword(),user.getStatus(),user.getCode(),user.getEmail());
    }

    /**
     * 根据code查询user
     * @param code
     * @return
     */
    @Override
    public User selectUserByCode(String code) {
        String sql = "select * from user where code = ?";
        User user = BasicDao.selectSingle(sql, User.class, code);
        return user;
    }

    /**
     * 修改用户的可用状态
     * @param code
     */
    @Override
    public void activeUser(String code) {
        //激活
        String sql = "update user set status = 'Y' where code = ?";
        BasicDao.update(sql,code);
    }

    /**
     * 用户登录的方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        String sql = "select * from user where username = ? and password = ?";
        User user1 = BasicDao.selectSingle(sql, User.class, user.getUsername(), user.getPassword());
        return user1;
    }
}
