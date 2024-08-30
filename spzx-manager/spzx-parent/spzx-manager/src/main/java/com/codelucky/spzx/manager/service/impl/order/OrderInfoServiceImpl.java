package com.codelucky.spzx.manager.service.impl.order;

import cn.hutool.core.date.DateUtil;
import com.codelucky.spzx.manager.mapper.order.OrderInfoMapper;
import com.codelucky.spzx.manager.mapper.order.OrderStatisticsMapper;
import com.codelucky.spzx.manager.service.order.OrderInfoService;
import com.codelucky.spzx.model.dto.order.OrderStatisticsDto;
import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.entity.order.OrderItem;
import com.codelucky.spzx.model.entity.order.OrderStatistics;
import com.codelucky.spzx.model.vo.order.OrderStatisticsVo;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    
    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    
    /**
     查询指定时间段订单信息
     @param orderStatisticsDto
     @return
     */
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        // 查询指定时间段内的订单统计结果
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.getOrderStatisticsData(orderStatisticsDto);
        
        if (CollectionUtils.isEmpty(orderStatisticsList)) {
            throw new CodeLuckyException(229, "所选时间段内没有订单数据");
        }
        
        // 从结果中获取时间数据
        List<String> dateList = orderStatisticsList.stream()
                                                   .map(orderStatistics -> {
                                                       // 对数据进行格式化
                                                       String dateTime =
                                                               DateUtil.format(orderStatistics.getOrderDate(),
                                                                               "yyyy" + "-MM-dd");
                                                       return dateTime;
                                                   })
                                                   .collect(Collectors.toList());
        
        // 从结果中获取金额数据
        List<BigDecimal> amountList = orderStatisticsList.stream()
                                                         .map(orderStatistics -> {
                                                             return orderStatistics.getTotalAmount();
                                                         })
                                                         .collect(Collectors.toList());
        
        // 封装结果 OrderStatisticsVo
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        return orderStatisticsVo;
    }
    
    /**
     分页查询订单列表数据
     @param keyword
     @param pageNum
     @param pageSize
     @return
     */
    @Override
    public PageInfo<OrderInfo> getOrderInfoList(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 查询订单列表
        List<OrderInfo> orderInfos = orderInfoMapper.getOrderInfoList(keyword);
        // 遍历订单列表设置时间格式
        orderInfos.forEach(item -> {
            item.setCancelTime(DateUtil.parse(DateUtil.format(item.getCancelTime(), "yyyy-MM-dd HH:mm:ss")));
            item.setDeliveryTime(DateUtil.parse(DateUtil.format(item.getDeliveryTime(), "yyyy-MM-dd HH:mm:ss")));
            item.setPaymentTime(DateUtil.parse(DateUtil.format(item.getPaymentTime(), "yyyy-MM-dd HH:mm:ss")));
            item.setReceiveTime(DateUtil.parse(DateUtil.format(item.getReceiveTime(), "yyyy-MM-dd HH:mm:ss")));
            item.setCreateTime(DateUtil.parse(DateUtil.format(item.getCreateTime(), "yyyy-MM-dd HH:mm:ss")));
        });
        // 遍历订单列表查询每个订单的订单项
        // 封装分页
        PageInfo pageInfo = new PageInfo<>(orderInfos);
        return pageInfo;
    }
    
    /**
     根据订单id查找对应的订单项
     @param orderId
     @return
     */
    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
        List<OrderItem> orderItemList = orderInfoMapper.getOrderItemByOrderId(orderId);
        return orderItemList;
    }
}
