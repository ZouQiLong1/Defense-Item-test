package com.item.ecrew.service;

import com.item.ecrew.domain.Cart;
import com.item.ecrew.domain.Order;

import java.util.List;

public interface CartService {
    /**
     * 查询购物车信息
     * @return
     */
    List<Cart> selectInfo(int id);

    /**
     * 根据userid查询order中的商品信息
     * @param id
     * @return
     */
    List<Order> selectOrderInfo(int id);

    /**
     * 删除购物车中的数据
     * @param cart
     * @return
     */
    boolean deleteCart(Cart cart);

    /**
     * 添加购物车的商品到订单
     * @param cart
     */
    void addToOrder(Cart cart);
}
