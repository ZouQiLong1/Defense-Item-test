package com.manage.ecrewManage.dao;

import com.manage.ecrewManage.domain.Manager;

public interface LoginDao {
    /**
     * 用户登录
     * @param manager1
     * @return
     */
    Manager login(Manager manager1);
}
