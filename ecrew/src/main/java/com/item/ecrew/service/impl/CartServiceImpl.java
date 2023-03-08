package com.item.ecrew.service.impl;

import com.item.ecrew.dao.CartDao;
import com.item.ecrew.dao.impl.CartDaoImpl;
import com.item.ecrew.domain.Cart;
import com.item.ecrew.domain.Order;
import com.item.ecrew.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    //dao层是在这个方法中公用的,因此我们可以提取出来
    private CartDao cartDao = new CartDaoImpl();

    /**
     * 查询购物车信息
     * @return
     */
    @Override
    public List<Cart> selectInfo(int id) {
        List<Cart> carts = cartDao.selectInfo(id);
        return carts;
    }
    //根据userId查出商品信息
    @Override
    public List<Order> selectOrderInfo(int id) {
        List<Order> orders = cartDao.selectOrderInfo(id);
        return orders;
    }

    /**
     * 删除购物车中的数据
     * @param cart
     * @return
     */
    @Override
    public boolean deleteCart(Cart cart) {
        //首先先查询数据库中是否有需要删除的这数据
        Cart cart1 = cartDao.selectCart(cart);
        //如果cart不为空，代表数据库中有数据
        if (cart1 != null){
            //数据库中存在数据，就可以删除了，防止出现异常
            int result = cartDao.deleteCart(cart);
            if (result > 0){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 添加购物车的商品到订单
     * @param cart
     */
    @Override
    public void addToOrder(Cart cart) {
        cartDao.addToOrder(cart);
    }
}
