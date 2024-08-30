package com.codelucky.spzx.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.codelucky.spzx.pay.properties.AlipayProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfiguration {
    @Resource
    private AlipayProperties alipayProperties;

    @Bean
    public AlipayClient alipayClient() {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getAlipayUrl(), alipayProperties.getAppId(), alipayProperties.getAppPrivateKey(), alipayProperties.format, alipayProperties.charset, alipayProperties.getAlipayPublicKey(), alipayProperties.sign_type);

        return alipayClient;
    }

}
