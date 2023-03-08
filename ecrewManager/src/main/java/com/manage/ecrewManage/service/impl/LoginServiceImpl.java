package com.manage.ecrewManage.service.impl;

import com.manage.ecrewManage.dao.LoginDao;
import com.manage.ecrewManage.dao.impl.LoginDaoImpl;
import com.manage.ecrewManage.domain.Manager;
import com.manage.ecrewManage.service.LoginService;

public class LoginServiceImpl implements LoginService {
    //因为dao层是页面上公用的，因此直接new在外面
    private LoginDao loginDao = new LoginDaoImpl();
    /**
     * 用户登录
     * @param manager1
     * @return
     */
    @Override
    public Manager login(Manager manager1) {
        Manager manager = loginDao.login(manager1);
        return manager;
    }
}
