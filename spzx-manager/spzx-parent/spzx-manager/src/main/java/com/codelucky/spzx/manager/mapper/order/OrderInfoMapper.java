package com.codelucky.spzx.manager.mapper.order;

import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.entity.order.OrderItem;
import com.codelucky.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    
    /**
     统计指定时间的订单信息
     @param dateTime
     @return
     */
    OrderStatistics selectOrderStatistics(String dateTime);
    
    /**
     查询订单列表数据
     @param keyword
     @return
     */
    List<OrderInfo> getOrderInfoList(String keyword);
    
    /**
     根据订单id查找对应的订单项
     @param orderId
     */
    List<OrderItem> getOrderItemByOrderId(Integer orderId);
}
