package com.codelucky.spzx.feign.order;

import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-order")
public interface OrderFeignClient {
    // 根据orderNo获取订单信息
    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable("orderNo") String orderNo);

    // 修改订单信息
    @GetMapping("/api/order/orderInfo/auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo, @PathVariable(value = "orderStatus") Integer orderStatus);
}
