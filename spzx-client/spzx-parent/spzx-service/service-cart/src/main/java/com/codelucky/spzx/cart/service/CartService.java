package com.codelucky.spzx.cart.service;

import com.codelucky.spzx.model.entity.h5.CartInfo;

import java.util.List;

public interface CartService {

    /**
     * 加入购物车
     *
     * @param skuId
     */
    void addToCart(Long skuId, Integer skuNum);

    /**
     * 查询购物车列表
     *
     * @return
     */
    List<CartInfo> getCartList();

    /**
     * 删除购物项
     *
     * @param skuId
     */
    void deleteCart(Long skuId);

    /**
     * 更新购物车商品选中状态
     *
     * @param skuId
     * @param isChecked
     */
    void checkCart(Long skuId, Integer isChecked);

    /**
     * 更新购物车商品全部选中状态
     *
     * @param isChecked
     */
    void allCheckCart(Integer isChecked);

    /**
     * 清空购物车
     */
    void clearCart();

    /**
     * 查询选中的购物车项
     *
     * @return
     */
    List<CartInfo> getAllCkecked();

    /**
     * 清除选中的购物项
     */
    void deleteChecked();
}
