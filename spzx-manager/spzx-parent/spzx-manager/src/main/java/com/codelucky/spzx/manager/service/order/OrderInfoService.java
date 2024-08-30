package com.codelucky.spzx.manager.service.order;

import com.codelucky.spzx.model.dto.order.OrderStatisticsDto;
import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.entity.order.OrderItem;
import com.codelucky.spzx.model.vo.order.OrderStatisticsVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderInfoService {
    /**
     查询指定时间段订单信息
     @param orderStatisticsDto
     @return
     */
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
    
    /**
     分页查询订单列表数据
     @param keyword
     @param pageNum
     @param pageSize
     @return
     */
    PageInfo<OrderInfo> getOrderInfoList(String keyword, int pageNum, int pageSize);
    
    /**
     根据订单id查找对应的订单项
     @param orderId
     @return
     */
    List<OrderItem> getOrderItemByOrderId(Integer orderId);
}
