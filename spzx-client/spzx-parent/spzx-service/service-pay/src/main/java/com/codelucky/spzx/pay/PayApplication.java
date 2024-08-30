package com.codelucky.spzx.pay;

import com.codelucky.spzx.pay.properties.AlipayProperties;
import com.codelucky.spzx.service.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableConfigurationProperties(value = AlipayProperties.class)
@EnableFeignClients(value = "com.codelucky.spzx.feign")
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
