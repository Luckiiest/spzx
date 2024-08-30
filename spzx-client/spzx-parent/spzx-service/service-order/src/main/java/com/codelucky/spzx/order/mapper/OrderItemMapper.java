package com.codelucky.spzx.order.mapper;


import com.codelucky.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    //添加数据到order_item表
    void save(OrderItem orderItem);

    //查询订单项列表
    List<OrderItem> getOrderItem(Long orderId);
}
