package com.codelucky.spzx.order;

import com.codelucky.spzx.service.anno.EnableUserLoginAuthInterceptor;
import com.codelucky.spzx.service.anno.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableUserTokenFeignInterceptor
@EnableUserLoginAuthInterceptor
@EnableFeignClients(basePackages = {"com.codelucky.spzx"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
