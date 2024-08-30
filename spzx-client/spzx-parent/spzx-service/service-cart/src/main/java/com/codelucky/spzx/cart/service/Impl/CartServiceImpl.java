package com.codelucky.spzx.cart.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.codelucky.spzx.cart.service.CartService;
import com.codelucky.spzx.feign.product.ProductFeignClient;
import com.codelucky.spzx.model.entity.h5.CartInfo;
import com.codelucky.spzx.model.entity.product.ProductSku;
import com.codelucky.spzx.model.entity.user.UserInfo;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.util.AuthContextUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private ProductFeignClient productFeignClient;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 加入购物车
     * <p>
     * 1. 存储格式和内容
     * redis: hash
     * key (field value)
     * key: userId
     * field: skuId
     * value: 商品信息数据
     * <p>
     * 2. 业务处理
     * 第一次存储数据, userId:1  skuId:18   value:cartInfo(skuNum+1)
     * 第二次存储数据, userId:1  skuId:17   value:cartIn
     * 第三次存储数据, userId:1  skuId:18   value:cartInfo
     * 结论
     * 如果加入的商品已存在,则数量+1, 如果不存在,则直接添加信息到购物车
     *
     * @param skuId
     */
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        // 获取用户的Id
        Long userId = AuthContextUtil.getUserInfo().getId();
        // 获取购物车中的存储key
        String cartKey = getCartKey(userId);
        // 判断是否存在当前加入的sku
        Boolean result = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        CartInfo cartInfo = new CartInfo();
        if (result) {
            String cartInfoStr = (String) redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
            // 转换
            cartInfo = JSON.parseObject(cartInfoStr, CartInfo.class);
            // 更新数量
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            // 更新时间
            cartInfo.setUpdateTime(new Date());
            // 更新状态
            cartInfo.setIsChecked(1);
        } else {
            // 获取当前sku的商品信息
            Result<ProductSku> productSkuResult = productFeignClient.getBySkuId(skuId);
            ProductSku productSku = productSkuResult.getData();
            // 创建购物车对象
            cartInfo = new CartInfo();
            cartInfo.setUserId(userId);
            cartInfo.setSkuId(skuId);
            cartInfo.setSkuNum(skuNum);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            // 是否选中 1 选中, 0 未选中
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
            cartInfo.setCreateTime(new Date());
        }
        // 新增
        redisTemplate.opsForHash().put(cartKey, skuId.toString(), JSON.toJSONString(cartInfo));
    }

    /**
     * 查询购物车列表
     *
     * @return
     */
    @Override
    public List<CartInfo> getCartList() {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        Long userId = userInfo.getId();
        // 获取购物车key
        String cartKey = getCartKey(userId);
        // 获取购物车信息
        List<Object> cartList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(cartList)) {
            // 便利转换类型
            List<CartInfo> cartInfoList = cartList.stream().map(cartInfo -> {
                CartInfo cart = JSON.parseObject((String) cartInfo, CartInfo.class);
                return cart;
            }).sorted((o1, o2) -> o1.getUpdateTime().compareTo(o2.getUpdateTime())).collect(Collectors.toList());
            // 集合排序
            return cartInfoList;
        }
        return new ArrayList<>();
    }

    /**
     * 删除购物项
     *
     * @param skuId
     */
    @Override
    public void deleteCart(Long skuId) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        Boolean result = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if (result) {
            redisTemplate.opsForHash().delete(cartKey, String.valueOf(skuId));
        }
    }

    /**
     * 更新购物车商品选中状态
     *
     * @param skuId
     * @param isChecked
     */
    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        Boolean result = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if (result) {
            String cartInfoJSON = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(cartInfoJSON, CartInfo.class);
            // 设置状态
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
        }
    }

    /**
     * 更新购物车商品全部选中状态
     *
     * @param isChecked
     */
    @Override
    public void allCheckCart(Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        List<Object> cartList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(cartList)) {
            cartList.stream().map(cart -> {
                CartInfo cartInfo = JSON.parseObject(cart.toString(), CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                return cartInfo;
            }).forEach(cartInfo -> {
                redisTemplate.opsForHash()
                             .put(cartKey, String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo));
            });
        }
    }

    /**
     * 清空购物车
     */
    @Override
    public void clearCart() {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        redisTemplate.delete(cartKey);
    }


    /**
     * 查询选中的购物车项
     *
     * @return
     */
    @Override
    public List<CartInfo> getAllCkecked() {
        Long id = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(id);
        // 获取所有的购物项数据
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream()
                                                    .map(cartInfo -> JSON.parseObject(cartInfo.toString(), CartInfo.class))
                                                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                                                    .collect(Collectors.toList());
            return cartInfoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteChecked() {
        Long id = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(id);
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(objectList)) {
            // 循环遍历购物车项s
            objectList.stream()
                      .map(cartInfo -> JSON.parseObject(cartInfo.toString(), CartInfo.class))
                      .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                      .forEach(cartInfo -> deleteCart(cartInfo.getSkuId()));
        }
    }

    /**
     * 获取购物车key
     *
     * @param userId
     * @return
     */
    private String getCartKey(Long userId) {
        return "user:cart:" + userId;
    }
}
