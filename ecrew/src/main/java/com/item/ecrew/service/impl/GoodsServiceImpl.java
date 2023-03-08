package com.item.ecrew.service.impl;

import com.item.ecrew.dao.GoodsDao;
import com.item.ecrew.dao.impl.GoodsDaoImpl;
import com.item.ecrew.domain.Cart;
import com.item.ecrew.domain.Commodity;
import com.item.ecrew.domain.Order;
import com.item.ecrew.domain.User;
import com.item.ecrew.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
    //dao层，是公用的，因此将dao层放在最外面
    private GoodsDao goodsDao = new GoodsDaoImpl();
    /**
     * 查询商品信息
     * @param id
     * @return
     */
    @Override
    public Commodity selectGoods(String id) {
        Commodity commodity = goodsDao.selectGoods(Integer.parseInt(id));
        return commodity;
    }

    /**
     * 添加购物车
     * @param cart
     */
    @Override
    public void addToCart(Cart cart) {
        //首先,先查询数据库中是否有userid和goods-id对应的数据
        Cart cart1 = goodsDao.selectCart(cart);
        if (cart1 != null){
            //如果cart1查询出来的对象存在的话,首先需要在数据库中删除这条数据
            //这个地方需要特别注意,在往数据库中插入新的数据的时候,需要删除原来的老的数据,不然会出现两条标识一样的数据
            goodsDao.deleteOld(cart1.getId());
            //拿出cart中的id,和user中原本携带的id相加.然后再插入数据
            String first = cart.getNumber();
            String second = cart1.getNumber();
            //将两次拿到的购物车中的数据加在一起,在设置给cart对象
            int all = Integer.parseInt(first) + Integer.parseInt(second);
            cart.setNumber(String.valueOf(all));
            goodsDao.addToCart(cart);
        } else {
            //如果cart对象不存在,就直接插入数据
            goodsDao.addToCart(cart);
        }
    }

    /**
     * 减少购物车数量
     * @param cart
     */
    @Override
    public void descToCart(Cart cart) {
        //首先,先查询数据库中是否有userid和goods-id对应的数据
        Cart cart1 = goodsDao.selectCart(cart);
        if (cart1 != null){
            //如果cart1查询出来的对象存在的话,首先需要在数据库中删除这条数据
            //这个地方需要特别注意,在往数据库中插入新的数据的时候,需要删除原来的老的数据,不然会出现两条标识一样的数据
            goodsDao.deleteOld(cart1.getId());
            //拿出cart中的id,和user中原本携带的id相加.然后再插入数据
            String first = cart.getNumber();
            String second = cart1.getNumber();
            //将两次拿到的购物车中的数据加在一起,在设置给cart对象
            int all = Integer.parseInt(second) - Integer.parseInt(first) ;
            if (all <= 1){
                all = 1;
            }
            cart.setNumber(String.valueOf(all));
            goodsDao.addToCart(cart);
        } else {
            //如果cart对象不存在,就直接插入数据
            goodsDao.addToCart(cart);
        }
    }

    /**
     * 添加到订单
     * @param order
     */
    @Override
    public void addToOrder(Order order) {
        //首先,先查询数据库中是否有userid和goods-id对应的数据
        Order order1 = goodsDao.selectOrder(order);
        if (order1 != null){
            //如果查询出来的order对象存在的话,拿出cart中的id,和user中原本携带的id相加.然后再插入数据
            String first = order1.getNumber();
            String second = order.getNumber();
            //将两次拿到的购物车中的数据加在一起,在设置给cart对象
            int all = Integer.parseInt(first) + Integer.parseInt(second);
            order.setNumber(String.valueOf(all));
            goodsDao.addToOrder(order);
        } else {
            //如果cart对象不存在,就直接插入数据
            goodsDao.addToOrder(order);
        }
    }
}
