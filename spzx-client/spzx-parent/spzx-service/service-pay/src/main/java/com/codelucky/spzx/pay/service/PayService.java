package com.codelucky.spzx.pay.service;

public interface PayService {
    /**
     * 支付宝下单
     *
     * @param orderNo
     * @return
     */
    String submitAlipay(String orderNo);

    /**
     * 退款实现
     * @param orderNo
     * @return
     */
    boolean refund(String orderNo);
}
