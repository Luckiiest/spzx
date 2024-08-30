package com.codelucky.spzx.pay.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.codelucky.spzx.model.entity.pay.PaymentInfo;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.pay.properties.AlipayProperties;
import com.codelucky.spzx.pay.service.PayService;
import com.codelucky.spzx.pay.service.PaymentInfoService;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Resource
    private PaymentInfoService paymentInfoService;
    @Resource
    private AlipayProperties alipayProperties;
    @Resource
    private AlipayClient alipayClient;


    /**
     * 支付宝下单
     *
     * @param orderNo
     * @return
     */
    @SneakyThrows  // lombok的注解，对外声明异常
    @Override
    public String submitAlipay(String orderNo) {

        //保存支付记录
        PaymentInfo paymentInfo = paymentInfoService.savePaymentInfo(orderNo);

        //创建API对应的request
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

        // 同步回调
        alipayRequest.setReturnUrl(alipayProperties.getReturnPaymentUrl());

        // 异步回调
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyPaymentUrl());

        // 准备请求参数 ，声明一个map 集合
        HashMap<String, Object> map = new HashMap<>();
        // 订单号
        map.put("out_trade_no", paymentInfo.getOrderNo());
        // 商家和支付宝签约的产品码
        map.put("product_code", "QUICK_WAP_WAY");
        // 支付金额(订单总金额)
        map.put("total_amount", paymentInfo.getAmount());
        // 订单标题
        map.put("subject", paymentInfo.getContent());
        alipayRequest.setBizContent(JSON.toJSONString(map));

        // 发送请求
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(alipayRequest);
        if (response.isSuccess()) {
            log.info("调用成功");
            return response.getBody();
        } else {
            log.info("调用失败");
            throw new CodeLuckyException(ResultCodeEnum.DATA_ERROR);
        }
    }

    /**
     * 退款实现
     * 退款成功判断说明：
     * 接口返回fund_change=Y为退款成功，
     * fund_change=N或无此字段值返回时需通过退款查询接口进一步确认退款状态。
     * 详见退款成功判断指导。
     * 注意，接口中code=10000，仅代表本次退款请求成功，不代表退款成功。
     *
     * @param orderNo
     * @return
     */
    @Override
    @SneakyThrows
    public boolean refund(String orderNo) {
        PaymentInfo paymentInfo = paymentInfoService.getPaymentInfoByOrderNo(orderNo);
        //初始化请求对象
        // 构造请求参数以调用接口
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        // 设置商户订单号
        model.setOutTradeNo(orderNo);
        // 设置支付宝交易号
        //model.setTradeNo("2014112611001004680073956707");
        // 设置退款金额
        model.setRefundAmount(String.valueOf(paymentInfo.getAmount()));
        // 设置退款包含的商品列表信息
        // 设置退款原因说明
        model.setRefundReason("正常退款");
        // 设置针对账期交易
        //model.setRelatedSettleConfirmNo("2024041122001495000530302869");
        // 设置退款请求号
        model.setOutRequestNo(orderNo);
        request.setBizModel(model);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("退款成功");
            String fundChange = response.getFundChange();
            if ("Y".equals(fundChange)) {
                return true;
            }
        }
        System.out.println("退款失败");
        // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
        // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
        // System.out.println(diagnosisUrl);
        return false;
    }
}
