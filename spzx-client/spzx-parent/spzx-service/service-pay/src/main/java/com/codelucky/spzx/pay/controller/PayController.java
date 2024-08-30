package com.codelucky.spzx.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.codelucky.spzx.feign.order.OrderFeignClient;
import com.codelucky.spzx.model.entity.pay.PaymentInfo;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.pay.properties.AlipayProperties;
import com.codelucky.spzx.pay.service.PayService;
import com.codelucky.spzx.pay.service.PaymentInfoService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/order/alipay")
public class PayController {

    @Resource
    private PayService payService;
    @Resource
    private AlipayProperties alipayProperties;
    @Resource
    private PaymentInfoService paymentInfoService;
    @Resource
    private OrderFeignClient orderFeignClient;

    /**
     * 退款实现
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/refund")
    public Result<Boolean> refund(@RequestParam("orderNo") String orderNo) {
        boolean flag = payService.refund(orderNo);
        return Result.build(flag, ResultCodeEnum.SUCCESS);
    }

    /**
     * 支付宝支付异步回调
     *
     * @param params
     * @return
     */
    @PostMapping("/callback/notify")
    @SneakyThrows
    public String notifyCallback(@RequestParam Map<String, String> params) {
        // 验证器签名
        boolean signVerified = false; //调用SDK验证签名

        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(), AlipayProperties.charset, AlipayProperties.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if (signVerified) {
            // 交易状态
            String tradeStatus = params.get("trade_status");
            // 订单号
            String outTradeNo = params.get("out_trade_no");
            // 实际金额
            BigDecimal totalAmount = new BigDecimal(params.get("total_amount"));
            // app_id
            String appId = params.get("app_id");


            // 根据out_trade_no获取支付记录
            PaymentInfo paymentInfo = paymentInfoService.getPaymentInfoByOrderNo(outTradeNo);
            if (paymentInfo != null && alipayProperties.getAppId().equals(appId) && paymentInfo.getAmount()
                                                                                               .compareTo(totalAmount) == 0) {
                // 判断订单状态
                if (tradeStatus.equals("TRADE_SUCCESS") || tradeStatus.equals("TRADE_FINISHED")) {
                    // 表示二次验证通过-处理支付后的业务,修改支付记录和数据
                    // 修改支付记录
                    paymentInfoService.updatePaymentInfo(params);
                    // 修改订单状态
                    orderFeignClient.updateOrderStatus(paymentInfo.getOrderNo(), 1);
                    // 修改商品销量和库存
                    return "success";
                }
            }
        } else {
            return "failure";
        }
        // 修改支付记录
        return "failure";
    }

    /**
     * /api/order/alipay/submitAlipay/{orderNo}
     * 支付宝下单
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/submitAlipay/{orderNo}")
    public Result<String> submitAlipay(@PathVariable("orderNo") String orderNo) {
        String form = payService.submitAlipay(orderNo);
        return Result.build(form, ResultCodeEnum.SUCCESS);
    }
}
