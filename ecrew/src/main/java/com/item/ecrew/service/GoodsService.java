package com.item.ecrew.service;

import com.item.ecrew.domain.Cart;
import com.item.ecrew.domain.Commodity;
import com.item.ecrew.domain.Order;
import com.item.ecrew.domain.User;

public interface GoodsService {
    /**
     * 查询商品信息
     * @param id
     * @return
     */
    Commodity selectGoods(String id);

    /**
     * 添加购物车
     * @param cart
     */
    void addToCart(Cart cart);

    /**
     * 添加到订单列表
     * @param order
     */
    void addToOrder(Order order);

    /**
     * 减少购物车中的数量
     * @param cart
     */
    void descToCart(Cart cart);
}
