package com.codelucky.spzx.pay.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

// com.codelucky.spzx.pay.properties;
@Data
@ConfigurationProperties(prefix = "spzx.alipay")
public class AlipayProperties {
    private String alipayUrl;
    private String appPrivateKey;
    public String alipayPublicKey;
    private String appId;
    public String returnPaymentUrl;
    public String notifyPaymentUrl;

    public final static String format = "json";
    public final static String charset = "utf-8";
    public final static String sign_type = "RSA2";
}