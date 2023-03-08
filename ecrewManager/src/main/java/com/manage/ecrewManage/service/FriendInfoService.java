package com.manage.ecrewManage.service;

import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.domain.Join;
import com.manage.ecrewManage.domain.PageBean;
import com.manage.ecrewManage.domain.User;

import java.util.List;
import java.util.Map;

public interface FriendInfoService {
    /**
     * 查询用户信息
     *
     * @return
     */
//    List<User> selectUser();

    /**
     * 分页查询用户信息
     *
     * @return
     */
    PageBean<Join> selectUserByPage(String currentPage, String pageSize, Map<String, String[]> parameterMap);

}



