package com.item.ecrew.service.impl;

import com.item.ecrew.dao.UserDao;
import com.item.ecrew.dao.impl.UserDaoImpl;
import com.item.ecrew.domain.User;
import com.item.ecrew.service.UserService;
import com.item.ecrew.utils.MailUtils;
import com.item.ecrew.utils.UuidUtil;

public class UserServiceImpl implements UserService {
    //因为dao层是很多地方都调用的，因此我们可以直接放在最外层
    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户的方法
     * @return
     */
    @Override
    public boolean register(User user) {
        User user1 = userDao.selectUser(user);
        //如果查询出来的user不为空，代表数据库已经存在user
        if (user1 != null){
            return false;
        } else {
            //在注册之前，首先我们需要给注册邮箱的人发送一封邮件，
            //让用户点击邮件来验证，如果用户不点击邮箱的话，就不能进行激活
            //首先要将用户的激活状态设置为未激活
            user.setStatus("N");
            String uuid = UuidUtil.getUuid();
            user.setCode(uuid);
            //使用工具类来发送邮件,发邮件的时候，要传递code参数，这样的话用户点击的时候才能拿到这个code来验证
            MailUtils.sendMail(user.getEmail(),"请点击<a href='http://localhost/user/activeUser?code="+user.getCode()+"'>激活</a>,以激活账户","激活用户");
            //如果不为空。就直接往数据库中插入数据
            userDao.register(user);
            return true;
        }

    }

    /**
     * 用户激活账号
     * @param code
     * @return
     */
    @Override
    public boolean activeUser(String code) {
        //先查询数据库中，有没有code对应的user对象
        User user = userDao.selectUserByCode(code);
        if (user == null){
            //如果不存在code相同的用户，就注册失败，返回false
            return false;
        } else {
            //如果存在code相同的用户，就注册成功，返回true
            userDao.activeUser(code);
            return true;
        }
    }

    /**
     * 用户登录的方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User user1 = userDao.login(user);
        return user1;
    }
}
