package com.codelucky.spzx.pay.mapper;

import com.codelucky.spzx.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentInfoMapper {
    /**
     * 获取支付记录
     *
     * @param orderNo
     * @return
     */
    PaymentInfo getByOrderNo(String orderNo);

    /**
     * 保存支付记录
     *
     * @param paymentInfo
     */
    void save(PaymentInfo paymentInfo);

    /**
     * 修改支付记录
     *
     * @param paymentInfo
     */
    void updateById(PaymentInfo paymentInfo);
}
