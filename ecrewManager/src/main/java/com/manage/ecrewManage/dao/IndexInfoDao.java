package com.manage.ecrewManage.dao;

import com.manage.ecrewManage.domain.User;

import java.util.List;
import java.util.Map;

public interface IndexInfoDao {
    /**
     * 查询首页的用户信息
     * @return
     */
    List<User> selectUser();

    /**
     * 查询数据库中的 总条数
     * @return
     */
    int selectTotalCount(Map<String, String[]> parameterMap);

    /**
     * 分页查询页面展示的list集合
     *
     * @param start
     * @param pageSize
     * @param parameterMap
     * @return
     */
    List<User> selectUserByPage(int start, int pageSize, Map<String, String[]> parameterMap);
}
