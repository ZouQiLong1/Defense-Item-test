package com.item.ecrew.dao;

import com.item.ecrew.domain.Join;

public interface JoinDao {
    /**
     * 查询加盟表中是否有数据
     * @param join
     * @return
     */
    Join selectJoin(Join join);

    /**
     * 加盟表中加入数据
     * @param join
     */
    void join(Join join);
}
