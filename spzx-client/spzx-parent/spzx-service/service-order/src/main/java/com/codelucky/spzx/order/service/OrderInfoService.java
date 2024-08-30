package com.codelucky.spzx.order.service;


import com.codelucky.spzx.model.dto.h5.OrderInfoDto;
import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.vo.h5.TradeVo;
import com.github.pagehelper.PageInfo;

public interface OrderInfoService {

    //结算
    TradeVo getTrade();

    //生成订单
    Long submitOrder(OrderInfoDto orderInfoDto);

    //获取订单信息
    OrderInfo getOrderInfoById(Long orderId);

    //立即购买
    TradeVo buy(Long skuId);

    //获取订单分页列表
    PageInfo<OrderInfo> getOrderInfoByOrderStatus(Integer pageSize, Integer pageNum, Integer orderStatus);

    //根据订单号查询订单信息
    OrderInfo getOrderInfoByOrderNo(String orderNo);

    //根据orderNo修改订单状态
    void updateOrderStatus(String orderNo, Integer orderStatus);
}
