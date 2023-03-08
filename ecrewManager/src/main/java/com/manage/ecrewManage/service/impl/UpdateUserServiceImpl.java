package com.manage.ecrewManage.service.impl;

import com.manage.ecrewManage.dao.UpdateUserDao;
import com.manage.ecrewManage.dao.impl.UpdateUserDaoImpl;
import com.manage.ecrewManage.domain.User;
import com.manage.ecrewManage.service.UpdateUserService;

import java.awt.*;

public class UpdateUserServiceImpl implements UpdateUserService {
    //因为dao层很多地方都是公用的,因此我们放在最外面
    private UpdateUserDao updateUserDao = new UpdateUserDaoImpl();
    /**
     * 根据页面id查询user对象
     * @param id
     * @return
     */
    @Override
    public User selectUser(int id) {
        User user = updateUserDao.selectUser(id);
        return user;
    }

    /**
     * 修改用户数据
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        //首先查找id对应的user对象
        User user1 = updateUserDao.selectUser(user.getId());
        if (user1 != null){
            //如果user存在就直接修改,返回真
            updateUserDao.updateUser(user.getId(),user.getPassword(),user.getStatus());
            return true;
        } else {
            //返回假
            return false;
        }
    }

    /**
     * 根据id删除数据库中的user
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        updateUserDao.deleteUser(id);
    }
    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteSelected(String[] ids) {
        //循环遍历接收的数组，每次遍历都调用dao层删除数据库中的数据
        for (String id : ids) {
            updateUserDao.deleteUser(Integer.parseInt(id));
        }
    }
    /**
     * 新增用户
     * @param user
     */
    @Override
    public boolean addUser(User user) {
        User user1 = updateUserDao.selectUserByUsername(user.getUsername());
        if (user1 == null){
            updateUserDao.addUser(user);
            return true;
        } else {
            return false;
        }
    }
}
