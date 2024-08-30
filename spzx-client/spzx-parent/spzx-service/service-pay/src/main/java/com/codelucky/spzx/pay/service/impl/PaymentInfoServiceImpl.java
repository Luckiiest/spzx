package com.codelucky.spzx.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.codelucky.spzx.feign.order.OrderFeignClient;
import com.codelucky.spzx.feign.product.ProductFeignClient;
import com.codelucky.spzx.model.dto.product.SkuSaleDto;
import com.codelucky.spzx.model.entity.order.OrderInfo;
import com.codelucky.spzx.model.entity.order.OrderItem;
import com.codelucky.spzx.model.entity.pay.PaymentInfo;
import com.codelucky.spzx.pay.mapper.PaymentInfoMapper;
import com.codelucky.spzx.pay.service.PaymentInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {
    @Resource
    private PaymentInfoMapper paymentInfoMapper;
    @Resource
    private OrderFeignClient orderFeignClient;
    @Resource
    private ProductFeignClient productFeignClient;

    /**
     * @param orderNo
     * @return
     */
    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {
        // 查询是否存在记录
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(orderNo);
        // 封装paymentInfo
        if (paymentInfo == null) {
            // 查询orderInfo
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
            // 封装paymentInfo
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setOrderNo(orderNo);
            // 支付类型
            paymentInfo.setPayType(orderInfo.getPayType());
            //paymentInfo.setOutTradeNo(orderNo);
            // 订单总金额
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            // 设置交易内容
            List<OrderItem> orderItemList = orderInfo.getOrderItemList();
            StringBuilder builder = new StringBuilder();
            for (OrderItem orderItem : orderItemList) {
                builder.append(orderItem.getSkuName()).append(",");
            }
            if (builder.length() > 100) {
                paymentInfo.setContent(builder.toString().substring(1, 100));
            } else {
                paymentInfo.setContent(builder.toString());
            }
            paymentInfo.setPaymentStatus(0);

            paymentInfoMapper.save(paymentInfo);
        }

        return paymentInfo;
    }

    /**
     * 根据订单号查询支付记录
     *
     * @param outTradeNo
     * @return
     */
    @Override
    public PaymentInfo getPaymentInfoByOrderNo(String outTradeNo) {
        return paymentInfoMapper.getByOrderNo(outTradeNo);
    }

    /**
     * 修改支付记录状态
     *
     * @param params
     */
    @Override
    public void updatePaymentInfo(Map<String, String> params) {
        // 判断
        PaymentInfo paymentInfo = getPaymentInfoByOrderNo(params.get("out_trade_no"));
        if (paymentInfo.getPaymentStatus().intValue() == 1) {
            return;
        }

        paymentInfo.setPaymentStatus(1);
        paymentInfo.setOutTradeNo(params.get("trade_no"));
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(JSON.toJSONString(params));
        paymentInfoMapper.updateById(paymentInfo);

        // 4、更新商品销量
        OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(paymentInfo.getOrderNo()).getData();
        List<SkuSaleDto> skuSaleDtoList = orderInfo.getOrderItemList().stream().map(item -> {
            SkuSaleDto skuSaleDto = new SkuSaleDto();
            skuSaleDto.setSkuId(item.getSkuId());
            skuSaleDto.setNum(item.getSkuNum());
            return skuSaleDto;
        }).collect(Collectors.toList());
        productFeignClient.updateSkuSaleNum(skuSaleDtoList);
    }
}
