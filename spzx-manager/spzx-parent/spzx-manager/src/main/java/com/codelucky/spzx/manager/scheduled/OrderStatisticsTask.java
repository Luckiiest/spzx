package com.codelucky.spzx.manager.scheduled;

import cn.hutool.core.date.DateUtil;
import com.codelucky.spzx.manager.mapper.order.OrderInfoMapper;
import com.codelucky.spzx.manager.mapper.order.OrderStatisticsMapper;
import com.codelucky.spzx.model.entity.order.OrderStatistics;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderStatisticsTask {
    
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;
    
    /**
     秒 分 时 日 月 周 年
     特点:
     代表每
     日 和 周 相冲的特点,其中一个必须为 ? 表示忽略
     */
    //@Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        String dateTime = DateUtil.offsetDay(new Date(), -1)
                                  .toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(dateTime);
        if (orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics);
        }
    }
}
