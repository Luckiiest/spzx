package com.codelucky.spzx.manager.controller.order;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.order.OrderInfoService;
import com.codelucky.spzx.model.dto.order.OrderStatisticsDto;
import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.entity.order.OrderItem;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.order.OrderStatisticsVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order/orderInfo")
@Tag(name = "订单信息")
public class OrderInfoController {
    
    @Resource
    private OrderInfoService orderInfoService;
    
    /**
     统计某个时间段内的订单
     @param orderStatisticsDto
     @return
     */
    @GetMapping("/getOrderStatisticsData")
    @Log(title = "订单信息模块-查询订单统计信息",
         businessType = 0,
         isSaveResponseData = false)
    public Result<OrderStatisticsVo> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatisticsData(orderStatisticsDto);
        return Result.build(orderStatisticsVo, ResultCodeEnum.SUCCESS);
    }
    
    /**
     分页查询订单列表数据
     @param keyword
     @param pageNum
     @param pageSize
     @return
     */
    @GetMapping("/getOrderInfoList/{pageNum}/{pageSize}")
    @Log(title = "订单信息模块-查询订单信息列表",
         businessType = 0,
         isSaveResponseData = false)
    public Result<PageInfo<OrderInfo>> getOrderInfoList(@PathVariable int pageNum, @PathVariable int pageSize,
                                                        @RequestParam(value = "keyword") String keyword) {
        PageInfo<OrderInfo> orderInfoList = orderInfoService.getOrderInfoList(keyword, pageNum, pageSize);
        return Result.build(orderInfoList, ResultCodeEnum.SUCCESS);
    }
    
    /**
     根据订单id查找对应的订单项
     @param orderId
     @return
     */
    @GetMapping("/getOrderItem/{orderId}")
    @Log(title = "订单信息模块-查询订单所属订单项信息",
         businessType = 0,
         isSaveResponseData = false)
    public Result<List<OrderItem>> getOrderItemListByOrderId(@PathVariable Integer orderId) {
        List<OrderItem> orderItemList = orderInfoService.getOrderItemByOrderId(orderId);
        return Result.build(orderItemList, ResultCodeEnum.SUCCESS);
    }
}
