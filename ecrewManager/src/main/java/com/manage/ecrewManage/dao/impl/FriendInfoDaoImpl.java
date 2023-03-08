package com.manage.ecrewManage.dao.impl;

import com.manage.ecrewManage.dao.CommodityInfoDao;
import com.manage.ecrewManage.dao.FriendInfoDao;
import com.manage.ecrewManage.domain.Commodity;
import com.manage.ecrewManage.domain.Join;
import com.manage.ecrewManage.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FriendInfoDaoImpl implements FriendInfoDao {
    /**
     * 查询首页的用户信息
     * @return
     */
    @Override
    public List<User> selectUser() {
        String sql = "select * from commodity";
        List<User> users = BasicDao.selectMultiply(sql, User.class);
        return users;
    }
    /**
     * 查询数据库中的 总条数
     * @return
     */
    @Override
    public int selectTotalCount(Map<String, String[]> parameterMap) {
        //模糊查询之后的总条数
        //定义一个初始化sql
        String sql = "select count(*) from tab_join where 1 = 1  ";
        //使用StringBuilder来保存sql，方便后续扩展
        StringBuilder stringBuilder = new StringBuilder(sql);
        //创建一个list集合，保存参数，必须要使用list集合
        ArrayList arrayList = new ArrayList();
        for (String key : parameterMap.keySet()) {
            String[] strings = parameterMap.get(key);
            //我们在明确的知道每个map的key对应的value只有一个，因此我们直接去string[0]
            String string = strings[0];
            //需要判断获取的参数，是不是还包含页面上的currentPage和pageSize
            if ("currentPage".equals(key) || "pageSize".equals(key) ){
                continue;
            }
            //判断一个每个查询的框中是否都有值，如果没值就不添加
            if (string != null && string.length() > 0){
                //拼接sql查询的字符串
                stringBuilder.append(" and "+ key +" like ? ");
                //将参数保存在数组中
                arrayList.add("%" + string + "%");
            }
        }
        //给sql重新赋值，使用拼接后的sql
        sql = stringBuilder.toString();
        //注意，传递参数的时候，需要传递集合转数组的值，因为sql语句是拼接的
        int totalCount = Integer.parseInt(BasicDao.selectSalary(sql,arrayList.toArray()).toString());
        return totalCount;
    }
    /**
     * 分页查询页面展示的list集合
     *
     * @param start
     * @param pageSize
     * @param parameterMap
     * @return
     */
    @Override
    public List<Join> selectUserByPage(int start, int pageSize, Map<String, String[]> parameterMap) {
        //模糊查询之后的总条数
        //定义一个初始化sql
        String sql = "select * from tab_join where 1 = 1  ";
        //使用StringBuilder来保存sql，方便后续扩展
        StringBuilder stringBuilder = new StringBuilder(sql);
        //创建一个list集合，保存参数，必须要使用list集合
        ArrayList arrayList = new ArrayList();
        for (String key : parameterMap.keySet()) {
            String[] strings = parameterMap.get(key);
            //我们在明确的知道每个map的key对应的value只有一个，因此我们直接去string[0]
            String string = strings[0];
            //需要判断获取的参数，是不是还包含页面上的currentPage和pageSize
            if ("currentPage".equals(key) || "pageSize".equals(key) || "id".equals(key) ){
                continue;
            }
            //判断一个每个查询的框中是否都有值，如果没值就不添加
            if (string != null && string.length() > 0){
                //拼接sql查询的字符串
                stringBuilder.append(" and "+ key +" like ? ");
                //将参数保存在数组中
                arrayList.add("%" + string +"%");
            }
        }
        //页面上接收的参数拼接完毕之后，拼接limit
        stringBuilder.append( "  limit ? , ? ");
        //将limit的参数保存到数组中
        arrayList.add(start);
        arrayList.add(pageSize);
        //给sql重新赋值，使用拼接后的sql
        sql = stringBuilder.toString();
        //注意，传递参数的时候，需要传递集合转数组的值，因为sql语句是拼接的
        List<Join> list = null;
        try {
            list = BasicDao.selectMultiply(sql, Join.class, arrayList.toArray());
        } catch (Exception e) {
            System.out.println(" 未查找到数据 ");
        }

        return list;
    }
}
