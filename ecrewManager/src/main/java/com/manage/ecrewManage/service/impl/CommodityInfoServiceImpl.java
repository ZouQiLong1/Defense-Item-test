package com.manage.ecrewManage.service.impl;

import com.manage.ecrewManage.dao.CommodityInfoDao;
import com.manage.ecrewManage.dao.IndexInfoDao;
import com.manage.ecrewManage.dao.impl.CommodityInfoDaoImpl;
import com.manage.ecrewManage.dao.impl.IndexInfoDaoImpl;
import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.domain.PageBean;
import com.manage.ecrewManage.domain.User;
import com.manage.ecrewManage.service.CommodityInfoService;
import com.manage.ecrewManage.service.IndexInfoService;

import java.util.List;
import java.util.Map;

public class CommodityInfoServiceImpl implements CommodityInfoService {
    //因为dao层是公用的，因此我们直接放在外面
    private CommodityInfoDao commodityInfoDao = new CommodityInfoDaoImpl();
    /**
     * 查询用户信息
     * @return
     */
    @Override
    public List<User> selectUser() {
        List<User> users = commodityInfoDao.selectUser();
        return users;
    }
    /**
     * 分页查询用户信息
     * @return
     */
    @Override
    public PageBean<Commodity> selectUserByPage(String _currentPage, String _pageSize, Map<String, String[]> parameterMap) {
        //创建一个pageBean，保存信息
        PageBean<Commodity> userPageBean = new PageBean<>();
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //再做前一页和后一页的时候，需要判断当前页是否小于1
        if (currentPage < 1){
            currentPage = 1;
        }
        //将当前页数也每页显示条数放在pageBean中
        userPageBean.setPageSize(pageSize);
        //查询数据库中的user的总数据
        int totalCount = commodityInfoDao.selectTotalCount(parameterMap);
        //将数据库中的总数设置在pageBean中
        userPageBean.setTotalCount(totalCount);
        //计算总页数、
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        if (currentPage > totalPage){
            currentPage = totalPage;
        }
        userPageBean.setCurrentPage(currentPage);
        //将总页数设置在pageBean中
        userPageBean.setTotalPage(totalPage);
        //根据当前页和每页显示条数，计算出查询的起始页和查询条数
        int start = (currentPage - 1 ) * pageSize;
        //调用dao层查询每页上展示的list集合
        List<Commodity> list = commodityInfoDao.selectUserByPage(start,pageSize,parameterMap);
        //将查询出来的user集合存放在pagaBean中
        userPageBean.setList(list);
        return userPageBean;

    }

}
