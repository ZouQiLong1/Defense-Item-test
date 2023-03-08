package com.item.ecrew.dao.impl;

import com.item.ecrew.dao.GoodsDao;
import com.item.ecrew.domain.Cart;
import com.item.ecrew.domain.Commodity;
import com.item.ecrew.domain.Order;

import java.util.Date;

public class GoodsDaoImpl implements GoodsDao {
    /**
     * 查询商品详情信息
     * @param id
     * @return
     */
    @Override
    public Commodity selectGoods(int id) {
        String sql = "select * from commodity where id = ?";
        Commodity commodity = BasicDao.selectSingle(sql, Commodity.class, id);
        return commodity;
    }

    /**
     * 查询购物车中是否有数据
     * @param cart
     * @return
     */
    @Override
    public Cart selectCart(Cart cart) {
        String sql = "select * from cart where goodsId = ? and userid = ?";
        Cart cart1 = BasicDao.selectSingle(sql, Cart.class, cart.getGoodsId(), cart.getUserId());
        return cart1;
    }

    /**
     * 添加到购物车
     * @param cart
     */
    @Override
    public void addToCart(Cart cart) {
        String sql = "insert into cart values(?,?,?,?)";
        BasicDao.update(sql,null,cart.getGoodsId(),cart.getUserId(),cart.getNumber());
    }

    /**
     * 删除原来的购物车数据
     * @param id
     */
    @Override
    public void deleteOld(int id) {
        String sql = "delete from cart where id = ?";
        BasicDao.update(sql,id);
    }
    /**
     * 查询购物车中是否有数据
     * @param order
     * @return
     */
    @Override
    public Order selectOrder(Order order) {
        String sql = "select * from tab_order where userId = ? and goodsId = ?";
        Order order1 = BasicDao.selectSingle(sql, Order.class, order.getUserId(), order.getGoodsId());
        return order1;
    }

    /**
     * 添加到购物车
     * @param order
     */
    @Override
    public void addToOrder(Order order) {
        String sql = "insert into tab_order values(?,?,?,?,?)";
        BasicDao.update(sql,null,order.getUserId(),order.getGoodsId(),new Date(),order.getNumber());
    }

}
