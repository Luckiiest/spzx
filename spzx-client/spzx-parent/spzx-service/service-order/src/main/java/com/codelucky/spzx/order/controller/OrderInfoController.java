package com.codelucky.spzx.order.controller;

import com.codelucky.spzx.model.dto.h5.OrderInfoDto;
import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.h5.TradeVo;
import com.codelucky.spzx.order.service.OrderInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单管理")
@RestController
@RequestMapping(value = "/api/order/orderInfo/auth")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    // 根据orderNo修改订单状态
    @GetMapping("/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    public Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo, @PathVariable(value = "orderStatus") Integer orderStatus) {
        orderInfoService.updateOrderStatus(orderNo, orderStatus);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //生成订单
    @Operation(summary = "提交订单")
    @PostMapping("/submitOrder")
    public Result submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.build(orderId, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "确认下单")
    @GetMapping("/trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取订单信息
     *
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public Result<OrderInfo> getOrderInfoById(@PathVariable("orderId") Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfoById(orderId);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 立即购买
     *
     * @param skuId
     * @return
     */
    @GetMapping("/buy/{skuId}")
    public Result<TradeVo> getBuyOrderInfoById(@PathVariable("skuId") Long skuId) {
        TradeVo tradeVo = orderInfoService.buy(skuId);
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * /api/order/orderInfo/auth/1/10/orderStatus=
     * 获取订单分页列表
     *
     * @return
     */
    @GetMapping("/{pageSize}/{pageNum}")
    public Result<PageInfo<OrderInfo>> getOrderInfoByOrderStatus(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum, @RequestParam(required = false, defaultValue = "") Integer orderStatus) {
        PageInfo<OrderInfo> orderInfoPageInfo = orderInfoService.getOrderInfoByOrderStatus(pageSize, pageNum, orderStatus);
        return Result.build(orderInfoPageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据订单号查询订单信息
     *
     * @param orderNo
     * @return
     */
    @GetMapping("getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable("orderNo") String orderNo) {
        OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(orderNo);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }
}
