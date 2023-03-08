package com.item.ecrew.dao.impl;

import com.item.ecrew.dao.JoinDao;
import com.item.ecrew.domain.Join;

public class JoinDaoImpl implements JoinDao {
    /**
     * 加盟表中查询数据
     * @param join
     * @return
     */
    @Override
    public Join selectJoin(Join join) {
        String sql = "select * from tab_join where username = ?";
        Join join1 = BasicDao.selectSingle(sql, Join.class, join.getUsername());
        return join1;
    }

    /**
     * 加盟表中加入数据
     * @param join
     */
    @Override
    public void join(Join join) {
        String sql = "insert into tab_join values(?,?,?)";
        BasicDao.update(sql,null,join.getUsername(),join.getTelephone());
    }
}
