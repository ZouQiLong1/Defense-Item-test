package com.item.ecrew.dao;

import com.item.ecrew.domain.Cart;
import com.item.ecrew.domain.Commodity;
import com.item.ecrew.domain.Order;

public interface GoodsDao {
    /**
     * 查询商品详情信息
     * @param id
     * @return
     */
    Commodity selectGoods(int id);

    /**
     * 查询数据库中有没有数据
     * @param cart
     * @return
     */
    Cart selectCart(Cart cart);

    /**
     * 往购物车表中添加数据
     * * @param cart
     */
    void addToCart(Cart cart);

    /**
     * 数据插入之前,需要删除老数据
     * @param id
     */
    void deleteOld(int id);

    /**
     * 添加订单表
     * @param order
     * @return
     */
    Order selectOrder(Order order);

    /**
     * 往订单表中添加数据
     * @param order1
     */
    void addToOrder(Order order1);
}
