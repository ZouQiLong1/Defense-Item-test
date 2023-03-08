package com.manage.ecrewManage.service;

import com.manage.ecrewManage.domain.Manager;

public interface LoginService {
    /**
     * 用户进行登录的方法
     * @param manager1
     * @return
     */
    Manager login(Manager manager1);
}
