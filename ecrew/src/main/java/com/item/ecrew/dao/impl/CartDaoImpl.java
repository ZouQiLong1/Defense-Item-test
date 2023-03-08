package com.item.ecrew.dao.impl;

import com.item.ecrew.dao.CartDao;
import com.item.ecrew.domain.Cart;
import com.item.ecrew.domain.Order;

import java.util.Date;
import java.util.List;

public class CartDaoImpl implements CartDao {
    /**
     * 查询购物车数据
     * @return
     */
    @Override
    public List<Cart> selectInfo(int id) {
        String sql = "select * from cart where userId = ?";
        List<Cart> carts = BasicDao.selectMultiply(sql, Cart.class, id);
        return carts;
    }

    /**
     * 根据userid，查询商品信息
     * @param id
     * @return
     */
    @Override
    public List<Order> selectOrderInfo(int id) {
        String sql = "select * from tab_order where userId = ?";
        List<Order> orders = BasicDao.selectMultiply(sql, Order.class, id);
        return orders;
    }

    /**
     * 删除购物车中的数据
     * @param cart
     * @return
     */
    @Override
    public int deleteCart(Cart cart) {
        String sql = "delete from cart where userid = ? and goodsId = ? ";
        int update = BasicDao.update(sql, cart.getUserId(), cart.getGoodsId());
        return update;
    }

    /**
     * 查看数据库中，是否有可以删除的数据
     * @param cart
     * @return
     */
    @Override
    public Cart selectCart(Cart cart) {
        String sql = "select * from cart where userid = ? and goodsId = ?";
        Cart cart1 = BasicDao.selectSingle(sql, Cart.class, cart.getUserId(), cart.getGoodsId());
        return cart1;
    }

    /**
     * 将购物车中的商品添加到订单表中
     * @param cart
     */
    @Override
    public void addToOrder(Cart cart) {
        String sql = "insert into tab_order values(?,?,?,?,?)";
        BasicDao.update(sql,null,cart.getUserId(),cart.getGoodsId(),new Date(),1);
    }
}
