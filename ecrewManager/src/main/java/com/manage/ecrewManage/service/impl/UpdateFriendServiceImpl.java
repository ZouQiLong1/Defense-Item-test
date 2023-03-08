package com.manage.ecrewManage.service.impl;

import com.manage.ecrewManage.dao.UpdateCommodityDao;
import com.manage.ecrewManage.dao.UpdateFriendDao;
import com.manage.ecrewManage.dao.impl.UpdateCommodityDaoImpl;
import com.manage.ecrewManage.dao.impl.UpdateFriendDaoImpl;
import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.service.UpdateCommodityService;
import com.manage.ecrewManage.service.UpdateFriendService;

public class UpdateFriendServiceImpl implements UpdateFriendService {
    //因为dao层很多地方都是公用的,因此我们放在最外面
    private UpdateFriendDao updateFriendDao = new UpdateFriendDaoImpl();
    /**
     * 根据页面id查询user对象
     * @param id
     * @return
     */
    @Override
    public Commodity selectUser(int id) {
        Commodity commodity = updateFriendDao.selectUser(id);
        return commodity;
    }

    /**
     * 修改用户数据
     * @param commodity
     * @return
     */
    @Override
    public boolean updateUser(Commodity commodity) {
        //首先查找id对应的user对象
        Commodity commodity1 = updateFriendDao.selectUser(commodity.getId());
        if (commodity1 != null){
            //如果user存在就直接修改,返回真
            updateFriendDao.updateUser(commodity.getId(),commodity.getTitle(),commodity.getDescription(),commodity.getPrice());
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
        updateFriendDao.deleteUser(id);
    }
    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteSelected(String[] ids) {
        //循环遍历接收的数组，每次遍历都调用dao层删除数据库中的数据
        for (String id : ids) {
            updateFriendDao.deleteUser(Integer.parseInt(id));
        }
    }
    /**
     * 新增用户
     * @param commodity
     */
//    @Override
//    public boolean addUser(Commodity commodity) {
//        Commodity commodity1 = updateCommodityDao.selectUserByUsername(commodity.getTitle());
//        if (commodity1 == null){
//            updateCommodityDao.addUser(commodity);
//            return true;
//        } else {
//            return false;
//        }
//    }
}
