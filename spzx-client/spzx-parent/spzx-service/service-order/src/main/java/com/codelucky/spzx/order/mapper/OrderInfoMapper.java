package com.codelucky.spzx.order.mapper;


import com.codelucky.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {

    //添加数据到order_info表
    void save(OrderInfo orderInfo);

    //查询订单信息
    OrderInfo getOrderInfoById(Long orderId);

    //获取订单分页列表
    List<OrderInfo> getOrderByStatus(Long userId, Integer orderStatus);

    //根据订单号查询订单信息
    OrderInfo getByOrderNo(String orderNo);

    // 修改订单信息
    void updateById(OrderInfo orderInfo);
}
