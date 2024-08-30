package com.codelucky.spzx.order.service.impl;


import com.codelucky.spzx.feign.cart.CartFeignClient;
import com.codelucky.spzx.feign.product.ProductFeignClient;
import com.codelucky.spzx.feign.user.UserFeignClient;
import com.codelucky.spzx.model.dto.h5.OrderInfoDto;
import com.codelucky.spzx.model.entity.h5.CartInfo;
import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.entity.order.OrderItem;
import com.codelucky.spzx.model.entity.order.OrderLog;
import com.codelucky.spzx.model.entity.product.ProductSku;
import com.codelucky.spzx.model.entity.user.UserAddress;
import com.codelucky.spzx.model.entity.user.UserInfo;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.h5.TradeVo;
import com.codelucky.spzx.order.mapper.OrderInfoMapper;
import com.codelucky.spzx.order.mapper.OrderItemMapper;
import com.codelucky.spzx.order.mapper.OrderLogMapper;
import com.codelucky.spzx.order.service.OrderInfoService;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import com.codelucky.spzx.util.AuthContextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private CartFeignClient cartFeignClient;

    @Resource
    private ProductFeignClient productFeignClient;

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private OrderLogMapper orderLogMapper;

    //结算
    @Override
    public TradeVo getTrade() {

        //远程调用获取购物车选中商品列表
        List<CartInfo> cartInfoList = cartFeignClient.getAllCkecked().getData();
        //创建list集合用于封装订单项
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }

        //获取订单支付总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }

        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalAmount);
        return tradeVo;
    }

    //生成订单
    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        //1 orderInfoDto获取所有订单项list List<OrderItem>
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();

        //2 判断List<OrderItem>为空，抛出异常
        if (CollectionUtils.isEmpty(orderItemList)) {
            throw new CodeLuckyException(ResultCodeEnum.DATA_ERROR);
        }

        //3 校验商品库存是否充足
        // 遍历List<OrderItem>集合，得到每个orderItem
        for (OrderItem orderItem : orderItemList) {
            //根据skuId获取sku信息，远程调用获取商品sku信息，（包含库存量）
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId()).getData();
            if (productSku == null) {
                throw new CodeLuckyException(ResultCodeEnum.DATA_ERROR);
            }
            // 校验每个orderItem库存量是否充足
            if (orderItem.getSkuNum().intValue() > productSku.getStockNum().intValue()) {
                throw new CodeLuckyException(ResultCodeEnum.STOCK_LESS);
            }
        }

        //4 添加数据到order_info表
        // 封装数据到OrderInfo对象
        OrderInfo orderInfo = new OrderInfo();
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        //订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        //用户id
        orderInfo.setUserId(userInfo.getId());
        //用户昵称
        orderInfo.setNickName(userInfo.getNickName());

        //封装收货地址信息
        Long userAddressId = orderInfoDto.getUserAddressId();
        //远程调用：根据收货地址id，获取用户收货地址信息
        UserAddress userAddress = userFeignClient.getUserAddress(userAddressId).getData();
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        //订单金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);

        orderInfoMapper.save(orderInfo);

        //5 添加数据到order_item表
        //添加List<OrderItem>里面数据，把集合每个orderItem添加表
        for (OrderItem orderItem : orderItemList) {
            //设置对应订单id
            orderItem.setOrderId(orderInfo.getId());
            orderItemMapper.save(orderItem);
        }

        //6 添加数据到order_log表
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);
        orderLog.setNote("提交订单");
        orderLogMapper.save(orderLog);

        //7 远程调用：把生成订单商品，从购物车删除
        cartFeignClient.deleteChecked();

        //8 返回订单id
        return orderInfo.getId();
    }

    /**
     * 获取订单信息
     *
     * @param orderId
     */
    @Override
    public OrderInfo getOrderInfoById(Long orderId) {
        // 查询订单信息
        OrderInfo orderInfo = orderInfoMapper.getOrderInfoById(orderId);
        //  查询订单项列表
        List<OrderItem> orderItemList = orderItemMapper.getOrderItem(orderId);
        // 封装数据
        orderInfo.setOrderItemList(orderItemList);
        return orderInfo;
    }

    /**
     * 立即购买
     *
     * @param skuId
     * @return
     */
    @Override
    public TradeVo buy(Long skuId) {
        // 查询商品
        ProductSku productSku = productFeignClient.getBySkuId(skuId).getData();
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(skuId);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setThumbImg(productSku.getThumbImg());
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItem.setSkuNum(1);
        orderItemList.add(orderItem);

        // 封装数据
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(productSku.getSalePrice());
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;
    }

    /**
     * 获取订单分页列表
     *
     * @param pageSize
     * @param pageNum
     * @param orderStatus
     * @return
     */
    @Override
    public PageInfo<OrderInfo> getOrderInfoByOrderStatus(Integer pageSize, Integer pageNum, Integer orderStatus) {
        PageHelper.startPage(pageSize, pageNum);
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<OrderInfo> orderInfoList = orderInfoMapper.getOrderByStatus(userId, orderStatus);

        if (CollectionUtils.isEmpty(orderInfoList)) {
            return new PageInfo<>(List.of());
        }

        orderInfoList.forEach(orderInfo -> {
            List<OrderItem> orderItem = orderItemMapper.getOrderItem(orderInfo.getId());
            orderInfo.setOrderItemList(orderItem);
        });

        // 返回包含分页信息的 PageInfo 对象
        return new PageInfo<>(orderInfoList);
    }

    /**
     * 根据订单号查询订单信息
     *
     * @param orderNo
     * @return
     */
    @Override
    public OrderInfo getOrderInfoByOrderNo(String orderNo) {
        OrderInfo orderInfo = orderInfoMapper.getByOrderNo(orderNo);
        List<OrderItem> orderItem = orderItemMapper.getOrderItem(orderInfo.getId());
        orderInfo.setOrderItemList(orderItem);
        return orderInfo;
    }

    /**
     * 根据orderNo修改订单状态
     *
     * @param orderNo
     * @param orderStatus
     */
    @Override
    public void updateOrderStatus(String orderNo, Integer orderStatus) {
        // 更新订单状态
        OrderInfo orderInfo = orderInfoMapper.getByOrderNo(orderNo);
        orderInfo.setOrderStatus(orderStatus);
        orderInfo.setPaymentTime(new Date());
        orderInfoMapper.updateById(orderInfo);

        // 记录日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(1);
        orderLog.setNote("支付宝支付成功");
        orderLogMapper.save(orderLog);
    }

}
