package com.codelucky.spzx.manager.mapper.order;

import com.codelucky.spzx.model.dto.order.OrderStatisticsDto;
import com.codelucky.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {
    /**
     保存订单统计结果
     @param orderStatistics
     */
    void insert(OrderStatistics orderStatistics);
    
    /**
     查询指定时间段订单信息
     @param orderStatisticsDto
     @return
     */
    List<OrderStatistics> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
