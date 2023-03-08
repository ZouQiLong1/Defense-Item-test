package com.manage.ecrewManage.service;

import com.manage.ecrewManage.domain.Commodity;

public interface UpdateFriendService {
    /**
     * 根据页面id查询user对象
     * @param parseInt
     * @return
     */
    Commodity selectUser(int parseInt);

    /**
     * 修改用户数据
     * @param commodity
     * @return
     */
    boolean updateUser(Commodity commodity);

    /**
     * 根据id删除数据库中的user
     * @param id
     */
    void deleteUser(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteSelected(String[] ids);

    /**
     * 新增用户
     * @param commodity
     */
//    boolean addUser(Commodity commodity);
}
