package com.item.ecrew.service;

import com.item.ecrew.domain.User;

public interface UserService {
    /**
     * 注册用户的方法
     * @return
     */
    boolean register(User user);

    /**
     * 用户进行激活账号的
     * @param code
     * @return
     */
    boolean activeUser(String code);

    /**
     * 用户进行登录的方法
     * @param user
     * @return
     */
    User login(User user);
}
