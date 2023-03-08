package com.item.ecrew.dao;

import com.item.ecrew.domain.Cart;
import com.item.ecrew.domain.Order;

import java.util.List;

public interface CartDao {
    /**
     * 查询购物车数据
     * @return
     */
    List<Cart> selectInfo(int id);

    /**
     * 根据userid查询出order信息
     * @param id
     * @return
     */
    List<Order> selectOrderInfo(int id);

    /**
     * 删除购物车中的数据
     * @param cart
     * @return
     */
    int deleteCart(Cart cart);

    /**
     * 查看购物车表中是否有可以删除的数据
     * @param cart
     * @return
     */
    Cart selectCart(Cart cart);

    /**
     * 将购物车中的商品添加到商品表
     * @param cart
     */
    void addToOrder(Cart cart);
}
