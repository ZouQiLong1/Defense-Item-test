package com.manage.ecrewManage.dao.impl;

import com.manage.ecrewManage.dao.LoginDao;
import com.manage.ecrewManage.domain.Manager;

public class LoginDaoImpl implements LoginDao {
    /**
     * 用户登录
     * @param manager
     * @return
     */
    @Override
    public Manager login(Manager manager) {
        String sql = "select * from manager where username = ? and password = ? and identity = ?";
        Manager manager1 = BasicDao.selectSingle(sql, Manager.class, manager.getUsername(), manager.getPassword(), manager.getIdentity());
        return manager1;
    }
}
