package com.codelucky.spzx.pay.service;

import com.codelucky.spzx.model.entity.pay.PaymentInfo;

import java.util.Map;

public interface PaymentInfoService {
    /**
     * 保存支付记录
     *
     * @param orderNo
     */
    PaymentInfo savePaymentInfo(String orderNo);

    /**
     * 根据out_trade_no获取支付记录
     *
     * @param outTradeNo
     * @return
     */
    PaymentInfo getPaymentInfoByOrderNo(String outTradeNo);

    /**
     * 修改支付记录
     *
     * @param params
     */
    void updatePaymentInfo(Map<String, String> params);
}
