package com.item.ecrew.service.impl;

import com.item.ecrew.dao.JoinDao;
import com.item.ecrew.dao.impl.JoinDaoImpl;
import com.item.ecrew.domain.Join;
import com.item.ecrew.domain.User;
import com.item.ecrew.service.JoinService;
import com.item.ecrew.utils.MailUtils;
import com.item.ecrew.utils.UuidUtil;

public class JoinServiceImpl implements JoinService {
    private JoinDao joinDao = new JoinDaoImpl();
    @Override
    public boolean join(Join join) {
        Join join1 = joinDao.selectJoin(join);
        //如果查询出来的user不为空，代表数据库已经存在user
        if (join1 != null){
            return false;
        } else {
            //用户不存在，直接插入数据
            joinDao.join(join);
            return true;
        }
    }
}
