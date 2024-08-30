package com.codelucky.spzx.cart.controller;

import com.codelucky.spzx.cart.service.CartService;
import com.codelucky.spzx.model.entity.h5.CartInfo;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order/cart/auth")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * /api/order/cart/auth/deleteChecked
     * 清除选中的购物项
     *
     * @return
     */
    @GetMapping(value = "/deleteChecked")
    public Result deleteChecked() {
        cartService.deleteChecked();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询选中的购物车项
     *
     * @return
     */
    @GetMapping("/getAllCkecked")
    public Result<List<CartInfo>> getAllCkecked() {
        List<CartInfo> cartInfoList = cartService.getAllCkecked();
        return Result.build(cartInfoList, ResultCodeEnum.SUCCESS);
    }

    /**
     * /api/order/cart/addToCart/7/1
     * 加入购物车
     *
     * @param skuId
     * @param skuNum
     * @return
     */
    @GetMapping("/addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable("skuId") Long skuId, @PathVariable("skuNum") Integer skuNum) {
        // 加入购物车
        cartService.addToCart(skuId, skuNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询购物车列表
     *
     * @return
     */
    @GetMapping("/cartList")
    public Result cartList() {
        List<CartInfo> cartInfoList = cartService.getCartList();
        return Result.build(cartInfoList, ResultCodeEnum.SUCCESS);
    }

    /**
     * /api/order/cart/deleteCart/7
     * 删除购物车中的购物项
     *
     * @param skuId
     * @return
     */
    @DeleteMapping("/deleteCart/{skuId}")
    public Result deleteCart(@PathVariable("skuId") Long skuId) {
        cartService.deleteCart(skuId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //    /api/order/cart/checkCart/8/0

    /**
     * 更新购物车商品选中状态
     *
     * @param skuId
     * @param isChecked
     * @return
     */
    @GetMapping("/checkCart/{skuId}/{isChecked}")
    public Result checkCart(@PathVariable("skuId") Long skuId, @PathVariable("isChecked") Integer isChecked) {
        cartService.checkCart(skuId, isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    ///api/order/cart/auth/allCheckCart/1

    /**
     * 更新购物车商品全部选中状态
     *
     * @param isChecked
     * @return
     */
    @GetMapping("/allCheckCart/{isChecked}")
    public Result allCheckCart(@PathVariable("isChecked") Integer isChecked) {
        cartService.allCheckCart(isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    ///api/order/cart/auth/clearCart

    /**
     * 清空购物车
     *
     * @return
     */
    @GetMapping("/clearCart")
    public Result clearCart() {
        cartService.clearCart();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
